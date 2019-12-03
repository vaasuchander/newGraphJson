/**
 * 
 */
package com.deloitte.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.deloitte.model.jsonSerialize.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author vbejjanki
 *
 */
public class PlaybookTask {

	private long playbookTaskId;

	private String name;

	private boolean isRepetable;

	private boolean isManual;

	@JsonSerialize(using = DateSerializer.class)
	private LocalDateTime constraintDateTime;

	@JsonSerialize(using = DateSerializer.class)
	private LocalDateTime actualStartDateAndTime;

	@JsonSerialize(using = DateSerializer.class)
	private LocalDateTime actualEndDateAndTime;

	private String memberName;

	private String ruleSetTagName;

	private boolean apiEnquiryDeployService;

	private boolean apiUndeployMaintainceService;

	private String apiVersion;

	private String changemanPackage;

	private String activityDescription;

	private int displayPoistion;

	private boolean isDeleted;

	private int actualDuration;

	@JsonSerialize(using = DateSerializer.class)
	private LocalDateTime plannedStartDateAndTime;

	@JsonSerialize(using = DateSerializer.class)
	private LocalDateTime plannedEndDateAndTime;

	private int plannedDuration;

	private boolean isValidationRequired;

	private int sequenceNumber;

	private String executionContactMail;

	private String uniqueId;

	private String aaasexecutionId;

	private String executionNote;

	private String manualActivityDescription;

	private boolean isCritical;

	private boolean isParent;

	private PredecessorConstraint predecessorConstraint;

	private ReleaseToEnvironment releaseToEnvironment;

	public long getPlaybookTaskId() {
		return playbookTaskId;
	}

	public void setPlaybookTaskId(long id) {
		this.playbookTaskId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRepetable() {
		return isRepetable;
	}

	public void setRepetable(boolean isRepetable) {
		this.isRepetable = isRepetable;
	}

	public boolean isManual() {
		return isManual;
	}

	public void setManual(boolean isManual) {
		this.isManual = isManual;
	}

	public LocalDateTime getConstraintDateTime() {
		return constraintDateTime;
	}

	public void setConstraintDateTime(LocalDateTime constantDateTime) {
		this.constraintDateTime = constantDateTime;
	}

	public LocalDateTime getActualStartDateAndTime() {
		return actualStartDateAndTime;
	}

	public void setActualStartDateAndTime(LocalDateTime actualStartDateandTime) {
		this.actualStartDateAndTime = actualStartDateandTime;
	}

	public LocalDateTime getActualEndDateAndTime() {
		return actualEndDateAndTime;
	}

	public void setActualEndDateAndTime(LocalDateTime actualEndDateandTime) {
		this.actualEndDateAndTime = actualEndDateandTime;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getRuleSetTagName() {
		return ruleSetTagName;
	}

	public void setRuleSetTagName(String ruleSetTagName) {
		this.ruleSetTagName = ruleSetTagName;
	}

	public boolean isApiEnquiryDeployService() {
		return apiEnquiryDeployService;
	}

	public void setApiEnquiryDeployService(boolean apiEnquiryDeployService) {
		this.apiEnquiryDeployService = apiEnquiryDeployService;
	}

	public boolean isApiUndeployMaintainceService() {
		return apiUndeployMaintainceService;
	}

	public void setApiUndeployMaintainceService(boolean apiUndeployMaintainceService) {
		this.apiUndeployMaintainceService = apiUndeployMaintainceService;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getChangemanPackage() {
		return changemanPackage;
	}

	public void setChangemanPackage(String changemanPackage) {
		this.changemanPackage = changemanPackage;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public int getDisplayPoistion() {
		return displayPoistion;
	}

	public void setDisplayPoistion(int displayPoistion) {
		this.displayPoistion = displayPoistion;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getActualDuration() {
		return actualDuration;
	}

	public void setActualDuration(int actualDuration) {
		this.actualDuration = actualDuration;
	}

	public int getPlannedDuration() {
		return plannedDuration;
	}

	public void setPlannedDuration(int plannedDuration) {
		this.plannedDuration = plannedDuration;
	}

	public boolean isValidationRequired() {
		return isValidationRequired;
	}

	public void setValidationRequired(boolean isValidationRequired) {
		this.isValidationRequired = isValidationRequired;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getExecutionContactMail() {
		return executionContactMail;
	}

	public void setExecutionContactMail(String executionContactMail) {
		this.executionContactMail = executionContactMail;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getAaasexecutionId() {
		return aaasexecutionId;
	}

	public void setAaasexecutionId(String aaasexecutionId) {
		this.aaasexecutionId = aaasexecutionId;
	}

	public String getExecutionNote() {
		return executionNote;
	}

	public void setExecutionNote(String executionNote) {
		this.executionNote = executionNote;
	}

	public String getManualActivityDescription() {
		return manualActivityDescription;
	}

	public void setManualActivityDescription(String manualActivityDescription) {
		this.manualActivityDescription = manualActivityDescription;
	}

	public boolean isCritical() {
		return isCritical;
	}

	public void setCritical(boolean isCritical) {
		this.isCritical = isCritical;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public PredecessorConstraint getPredecessorConstraint() {
		return predecessorConstraint;
	}

	public void setPredecessorConstraint(PredecessorConstraint predecessorConstraint) {
		this.predecessorConstraint = predecessorConstraint;
	}

	public LocalDateTime getPlannedEndDateAndTime() {
		return plannedEndDateAndTime;
	}

	public void setPlannedEndDateAndTime(LocalDateTime plannedEndDateAndTime) {
		this.plannedEndDateAndTime = plannedEndDateAndTime;
	}

	public LocalDateTime getPlannedStartDateAndTime() {
		return plannedStartDateAndTime;
	}

	public void setPlannedStartDateAndTime(LocalDateTime plannedStartDateAndTime) {
		this.plannedStartDateAndTime = plannedStartDateAndTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(playbookTaskId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaybookTask other = (PlaybookTask) obj;
		return playbookTaskId == other.playbookTaskId;
	}

	public ReleaseToEnvironment getReleaseToEnvironment() {
		return releaseToEnvironment;
	}

	public void setReleaseToEnvironment(ReleaseToEnvironment releaseToEnvironment) {
		this.releaseToEnvironment = releaseToEnvironment;
	}

}
