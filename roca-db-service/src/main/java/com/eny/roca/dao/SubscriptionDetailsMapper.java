package com.eny.roca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eny.roca.db.bean.SubscriptionBean;

public class SubscriptionDetailsMapper implements RowMapper<SubscriptionBean> {

	@Override
	public SubscriptionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubscriptionBean subscriptionBean = new SubscriptionBean();
		subscriptionBean.setSubscriptionId(rs.getInt("id"));
		subscriptionBean.setLegalEntityName(rs.getString("LegalEntityName"));
		subscriptionBean.setPseudonym(rs.getString("pseudonym"));
		subscriptionBean.setCountryName(rs.getString("CountryName"));
		subscriptionBean.setTaxResidentialStatus(rs.getInt("TaxResidentialStatus"));
		subscriptionBean.setBodyCorporates(rs.getString("BodyCorporates"));
		subscriptionBean.setIsCharitable(rs.getBoolean("IsCharitableOrNonProfitable"));
		subscriptionBean.setIndustryId(rs.getString("IndustryId"));// Need to query to get industry..
		subscriptionBean.setCompanyHqLocation(rs.getString("cm1CountryName"));
		subscriptionBean.setPan(rs.getString("Pan"));
		subscriptionBean.setPanComments(rs.getString("PanComments"));
		subscriptionBean.setGst(rs.getString("Gst"));
		subscriptionBean.setGstComments(rs.getString("GstComments"));
		subscriptionBean.setUrl(rs.getString("Url"));
		subscriptionBean.setAddress(rs.getString("Address"));
		subscriptionBean.setContactPerson(rs.getString("ContactPerson"));
		subscriptionBean.setRoleName(rs.getString("RoleDesc")); // it has to be role_description to dispy on ui
		subscriptionBean.setEmailId(rs.getString("EmailId"));
		subscriptionBean.setMobileNo(rs.getLong("mobileNumber"));
		subscriptionBean.setWorkedWithEY(rs.getInt("WorkedWithEY"));
		subscriptionBean.setEyContactPerson1(rs.getString("EYContactPerson1"));
		subscriptionBean.setEyContactPerson2(rs.getString("EYContactPerson2"));
		subscriptionBean.setRocaServiceAvailed(rs.getInt("IsRocaServiceAvailed"));
		subscriptionBean.setRelatedPartyName1(rs.getString("RelatedPartyName1"));
		subscriptionBean.setRelatedPartyName2(rs.getString("RelatedPartyName2"));
		subscriptionBean.setStatus(rs.getString("Status"));
		
		subscriptionBean.setCreatedDate(rs.getString("CreatedOn"));
		subscriptionBean.setUpdatedDate(rs.getString("UpdatedOn"));
		subscriptionBean.setPaceId(rs.getString("PaceId"));
		//Need to add role description...
		return subscriptionBean;
	}

	
}
