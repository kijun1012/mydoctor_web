package com.mydoctor.vo;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IhealthData {
   private String accessToken;
   private String user_open_id;
   private String username;
}