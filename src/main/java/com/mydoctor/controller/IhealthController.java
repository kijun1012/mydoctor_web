package com.mydoctor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mydoctor.dao.BloodPressureDao;
import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.BloodSugar;
import com.mydoctor.model.User;
import com.mydoctor.model.Weight;
import com.mydoctor.module.JsonPassingModule;
import com.mydoctor.module.MyHttpModule;
import com.mydoctor.service.BloodPressureService;
import com.mydoctor.service.BloodSugarService;
import com.mydoctor.vo.IhealthData;
import com.mydoctor.vo.IhealthDataCategory;

@RestController
@RequestMapping("/ihealth")
public class IhealthController {

	private final String BPSC = "10eda0dc26b94cf78450a5ba62186806";
	private final String BPSV = "5d995c97be8748059e22310169ea14eb";
	private final String clientId = "836c0bba2dcf4359b1cfedd527f8870a";
	private final String clientSecret = "bc75b752768e41869efeda1c071f19bc";
	private final String BGSC = "10eda0dc26b94cf78450a5ba62186806";
	private final String BGSV = "77536ef99f044e738394b216707be8fc";
	private final String WEIGHT_SC = "10eda0dc26b94cf78450a5ba62186806";
	private final String WEIGHT_SV = "d00ed5569b4e4c16b3e4c276ac102101";
	private String user_open_id;
	private String accessToken;

	static AuthenticationManager am = new SampleAuthenticationManager();

	private List<BloodPressure> bpList;
	private List<BloodSugar> bGList;
	private List<Weight> weightList;
	private List<IhealthDataCategory> categoryList;

	@Autowired
	private BloodPressureDao bpDao;

	@Autowired
	private BloodPressureService bloodPressureService;

	@Autowired
	private BloodSugarService bloodSugarService;

	/**
	 * getBp(). getBG(). getWeight...매소드를 실행하면 각model에 측정날짜가 들어감 DB에서 가장 최근의
	 * 데이터를 뽑아와(혈압, 혈당, 몸무게....) List에 ihealth에서 읽어온 data를
	 * list.get(i).getDate()를해서 각 항목별 측정날짜를 다뽑아 측정날짜가 더 큰 걸 db에 넣고
	 * IhealthDataCategory객체만들고 List로만들어서 Android에 주면 됨
	 */

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public IhealthData setIhealthData(@RequestBody IhealthData ihealthData)
			throws ClientProtocolException, IOException {
		this.user_open_id = ihealthData.getUser_open_id();
		this.accessToken = ihealthData.getAccessToken();

		getBP();
		getBG();
		getWeight();
		return ihealthData;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestBody User user)
			throws ClientProtocolException, IOException {
		System.out.println(user.getId());
		System.out.println(user.getPassword());
		
		try{
			Authentication request = new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());
			Authentication result = am.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			
		}catch(AuthenticationException e){
			System.out.println("Authentication failed: "+e.getMessage());
		}
		System.out.println("success"+SecurityContextHolder.getContext().getAuthentication());;
		
		return user;
	}

	// ------------------------혈압
	public void getBP() throws ClientProtocolException, IOException {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		IhealthDataCategory bpC = new IhealthDataCategory();
		StringBuilder query = new StringBuilder();
		query.append("https://api.ihealthlabs.com:8443/openapiv2/user/" + user_open_id + "/bp.json/?");
		query.append("client_id=" + clientId + "&");
		query.append("client_secret=" + clientSecret + "&");
		query.append("access_token=" + accessToken + "&");
		query.append("sc=" + BPSC + "&");
		query.append("sv=" + BPSV);

		MyHttpModule module = new MyHttpModule();
		JSONObject obj = module.requestToServerUsingGetJSON(query.toString());
		int count = obj.getInt("RecordCount");
		JSONArray jsonArray = obj.getJSONArray("BPDataList");

		bpList = JsonPassingModule.jsonArrayToObject(jsonArray, BloodPressure.class);

		this.bloodPressureService.addBloodPressure(bpList, userId);
	}

	// ----------------------혈당
	public void getBG() {
		StringBuilder query = new StringBuilder();
		query.append("https://api.ihealthlabs.com:8443/openapiv2/user/" + user_open_id + "/glucose.json/?");
		query.append("client_id=" + clientId + "&");
		query.append("client_secret=" + clientSecret + "&");
		query.append("access_token=" + accessToken + "&");
		query.append("sc=" + BGSC + "&");
		query.append("sv=" + BGSV);
	}

	// -----------------------몸무게
	public void getWeight() {
		StringBuilder query = new StringBuilder();
		query.append("https://api.ihealthlabs.com:8443/openapiv2/user/" + user_open_id + "/weight.json/?");
		query.append("client_id=" + clientId + "&");
		query.append("client_secret=" + clientSecret + "&");
		query.append("access_token=" + accessToken + "&");
		query.append("sc=" + this.WEIGHT_SC + "&");
		query.append("sv=" + this.WEIGHT_SV);
	}
}
class SampleAuthenticationManager implements AuthenticationManager{
	static final List AUTHORITIES = new ArrayList();
	static{
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));

	}
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication.getName().equals(authentication.getCredentials())){
			return new UsernamePasswordAuthenticationToken(authentication.getName(),
					authentication.getCredentials(), AUTHORITIES);
		}
		// TODO 자동 생성된 메소드 스텁
		throw new BadCredentialsException("Bad");
	}
	
}
