package com.mydoctor.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydoctor.model.Weight;
import com.mydoctor.service.WeightService;

@Controller
public class WeightController {

	@Autowired
	private WeightService weightService;

	@RequestMapping("/weight")
	public String step(Model model, HttpServletRequest request) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Weight> weights = this.weightService.getAllWeight(userId);
		model.addAttribute("weights", weights);
		
		//System.out.println(weights.get(0).toString());

		if (request.getQueryString() != null) {
			StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");
			if (st.nextToken().equals("webview")) {
				String id = st.nextToken();
				List<Weight> weightsWeb = this.weightService.getAllWeight(id);
				model.addAttribute("weights", weightsWeb);
			}
			return "webview_weight";
		} else {
			return "weight";
		}

	}

}