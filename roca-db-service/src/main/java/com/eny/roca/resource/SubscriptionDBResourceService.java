package com.eny.roca.resource;

import java.io.IOException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eny.roca.config.RocaBlDbServiceConfig;
import com.eny.roca.dao.MasterDataDao;
import com.eny.roca.dao.SubscriptionDao;
import com.eny.roca.db.bean.StatusBean;
import com.eny.roca.db.bean.SubscriptionAssignment;
import com.eny.roca.db.bean.SubscriptionAssignmentBean;
import com.eny.roca.db.bean.SubscriptionBean;
import com.eny.roca.db.bean.SubscriptionDocDetails;
import com.eny.roca.db.bean.SubscriptionRequestBean;
import com.eny.roca.db.bean.UploadFileResponse;
import com.eny.roca.db.bean.UserBean;
import com.google.gson.Gson;

@RestController
@RequestMapping("/rs/db")
public class SubscriptionDBResourceService {

	@Autowired
	private SubscriptionDao subscriptionDao;

	@Autowired
	private MasterDataDao masterDataDao;
	@Autowired
	private RocaBlDbServiceConfig rocaBlDbServiceConfig;

	@PostMapping("/subscribe")
	public Boolean subscribe(@RequestBody SubscriptionBean subscriptionBean) {
		return subscriptionDao.saveSubscriptionUser(subscriptionBean) > 0 ? true : false;
	}

	@PostMapping("/usersubscribed/{emailId}")
	public Integer userSubscribed(@PathVariable("emailId") String emailid) {
		return subscriptionDao.userSubscribed(emailid);
	}

	// fetchUserSubscription
	@PostMapping("/fetchUserSubscription")
	public SubscriptionBean fetchUserSubscription(@RequestBody UserBean userBean) {
		return subscriptionDao.fetchUserSubscription(userBean.getEmailId());
	}

	@Transactional(rollbackFor = Exception.class)
	@PostMapping("/subscriptionAssignment")
	public Boolean subscriptionAssignment(@RequestBody List<SubscriptionAssignment> subscriptionAssignment) {
		return subscriptionDao.saveSubscriptionAssignment(subscriptionAssignment) > 0 ? true : false;
	}

	// Status
	@PostMapping("/fetchUserSubscriptionStatus")
	public List<SubscriptionBean> fetchUserSubscriptionStatus(@RequestBody UserBean userBean) {
		List<SubscriptionBean> subscriptionBeans = getSubscriptionStatus(userBean);
		subscriptionBeans.stream().forEach(s -> s.setIndustryName(getIndustryNames(s.getIndustryId())));
		return subscriptionBeans;
	}

	private String getIndustryNames(String industryId) {
		List<Object> industryName = masterDataDao.getIndustryName(industryId);
		List<String> collect = industryName.stream().map(object -> Objects.toString(object, null))
				.collect(Collectors.toList());
		return collect.stream().collect(Collectors.joining(","));

	}

	private List<SubscriptionBean> getSubscriptionStatus(UserBean userBean) {
		return subscriptionDao.fetchUserSubscriptionStatus(userBean.getEmailId(), userBean.getStatus(),
				userBean.getSubscriptionId());
	}

	@Transactional(rollbackFor = Exception.class)
	@PostMapping("/updatePaceId")
	public Boolean updatePaceId(@RequestBody List<SubscriptionAssignment> subscriptionAssignment) {
		return subscriptionDao.updateSubscriptionPaceId(subscriptionAssignment) > 0 ? true : false;
	}

	@PostMapping("/updateAdditionalDocRequired")
	public Boolean updateAdditionalDocRequired(@RequestBody StatusBean statusBean) {
		return subscriptionDao.updateAdditionalDocRequired(statusBean) > 0 ? true : false;
	}

	@PostMapping("/updateStatus")
	public Boolean updateStatus(@RequestBody List<StatusBean> StatusBean) {
		return subscriptionDao.updateStatus(StatusBean) > 0 ? true : false;
	}

	@RequestMapping(value = "/uploadFile", method = { RequestMethod.POST })
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("bean") String reqBean)
			throws IOException {

		Gson gson = new Gson();
		SubscriptionRequestBean bean = gson.fromJson(reqBean, SubscriptionRequestBean.class);

		SubscriptionBean dataToSave = new SubscriptionBean();

		dataToSave.setDocData(file);
		dataToSave.setDocExtention(file.getContentType());
		dataToSave.setDocName(bean.getDocName());
		dataToSave.setDocExtention(file.getContentType());
		dataToSave.setDocType(bean.getDocType());
		dataToSave.setEmail(bean.getEmail());
		dataToSave.setIs_valid_doc(bean.getIs_valid_doc());
		dataToSave.setSubscriptionId(bean.getSubscriptionId());
		SubscriptionDocDetails uploadFile = subscriptionDao.saveFile(dataToSave);

		String fileDownloadUri = rocaBlDbServiceConfig.getUrl() + "downloadFile/" + uploadFile.getDocName();

		return new UploadFileResponse(uploadFile.getDocName(), fileDownloadUri, uploadFile.getDocExtention(),
				uploadFile.getDocDataSize());
	}

	@RequestMapping(value = "/downloadFile", method = { RequestMethod.GET })
	public @ResponseBody byte[] downloadFile(@RequestParam String fileName) {

		List<SubscriptionDocDetails> fileData = null;

		byte[] docData = null;

		try {

			fileData = subscriptionDao.getFile(fileName);
			for (SubscriptionDocDetails subDetailsDoc : fileData) {

				docData = subDetailsDoc.getFileData();

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return docData;

	}

	@PostMapping("/fetchSubscriptionById")
	public SubscriptionBean fetchSubscriptionById(@RequestBody UserBean userBean) {
		return subscriptionDao.fetchSubscriptionDetailsById(userBean.getSubscriptionId());
	}
	
	@PostMapping("/fetchSubscriptionAssignmentById")
	public List<SubscriptionAssignmentBean> fetchSubscriptionAssignmentById(@RequestBody UserBean userBean) {
		return subscriptionDao.getSubscriptionAssignments(userBean.getSubscriptionId());
	}
	
	@PostMapping("/validateSubscriptionMobileNo")
	public Integer validateMobileNo(@RequestBody Long mobileNo) {
		return subscriptionDao.validateMobileNo(mobileNo);
	}
}
