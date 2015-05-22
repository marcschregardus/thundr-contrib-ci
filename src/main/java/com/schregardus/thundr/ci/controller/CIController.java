package com.schregardus.thundr.ci.controller;

import com.atomicleopard.expressive.Expressive;
import com.threewks.thundr.view.View;
import com.threewks.thundr.view.handlebars.HandlebarsView;
import com.threewks.thundr.view.json.JsonView;
import com.threewks.thundr.view.string.StringView;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * CI_BUILD_NUMBER	ID of the build in our service
 * CI_BUILD_URL	URL of the build
 * CI_PULL_REQUEST	false
 * CI_BRANCH	Branch of the build
 * CI_COMMIT_ID	Commit Hash of the build
 * CI_COMMITTER_NAME	Name of the committer
 * CI_COMMITTER_EMAIL	Email of the committer
 * CI_COMMITTER_USERNAME	Username of the commiter in their SCM service
 * CI_MESSAGE	Message of the last commit for that build
 */
public class CIController {

	private final String ciBuildNumber;
	private final String ciBuildUrl;
	private final String ciBranch;
	private final String ciCommitId;
	private final String ciCommitterName;
	private final String ciCommitterEmail;
	private final String ciCommitterUsername;
	private final String ciMessage;

	public CIController(String ciBuildNumber, String ciBuildUrl, String ciBranch, String ciCommitId, String ciCommitterName,
			String ciCommitterEmail, String ciCommitterUsername,
			String ciMessage) {
		this.ciBuildNumber = ciBuildNumber;
		this.ciBuildUrl = ciBuildUrl;
		this.ciBranch = ciBranch;
		this.ciCommitId = ciCommitId;
		this.ciCommitterName = ciCommitterName;
		this.ciCommitterEmail = ciCommitterEmail;
		this.ciCommitterUsername = ciCommitterUsername;
		this.ciMessage = ciMessage;
	}

	@SuppressWarnings("unused")
	public View overview(HttpServletRequest request) {

		Map<String, Object> ciMap = Expressive.map();
		ciMap.put("buildNumber", ciBuildNumber);
		ciMap.put("buildUrl", ciBuildUrl);
		ciMap.put("branch", ciBranch);
		ciMap.put("commitId", ciCommitId);
		ciMap.put("committerName", ciCommitterName);
		ciMap.put("committerEmail", ciCommitterEmail);
		ciMap.put("committerUsername", ciCommitterUsername);
		ciMap.put("message", ciMessage);

		String contentType = StringUtils.trimToEmpty(request.getContentType()).toLowerCase();
		if (StringUtils.contains(contentType, "json")) {
			return new JsonView(ciMap);
		} else {
			return new HandlebarsView("/hbs/ci.hbs", ciMap);
		}
	}

	@SuppressWarnings("unused")
	public StringView buildNumber() {
		return new StringView(ciBuildNumber);
	}

	@SuppressWarnings("unused")
	public StringView buildUrl() {
		return new StringView(ciBuildUrl);
	}

	@SuppressWarnings("unused")
	public StringView branch() {
		return new StringView(ciBranch);
	}

	@SuppressWarnings("unused")
	public StringView commitId() {
		return new StringView(ciCommitId);
	}

	@SuppressWarnings("unused")
	public StringView committerName() {
		return new StringView(ciCommitterName);
	}

	@SuppressWarnings("unused")
	public StringView committerEmail() {
		return new StringView(ciCommitterEmail);
	}

	@SuppressWarnings("unused")
	public StringView committerUsername() {
		return new StringView(ciCommitterUsername);
	}

	@SuppressWarnings("unused")
	public StringView message() {
		return new StringView(ciMessage);
	}

}
