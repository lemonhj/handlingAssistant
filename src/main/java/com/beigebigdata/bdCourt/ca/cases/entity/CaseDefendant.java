package com.beigebigdata.bdCourt.ca.cases.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "bc_case_ana_dfd_term")
public class CaseDefendant extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 案件编号
     */
    @Column(name = "case_no")
    private Long caseNo;

    /**
     * 案件涉及被告姓名
     */
    @Column(name = "defendant_name")
    private String defendantName;

    /**
     * 案件涉及被告人标识
     */
    @Column(name = "defendant_id")
    private String defendantId;

    /**
     * 案件涉及被告人证件类别
     */
    @Column(name = "id_type")
    private Integer idType;

    /**
     * 基础刑期下限(月)
     */
    @Column(name = "penalty_term_min")
    private Integer penaltyTermMin;

    /**
     * 基础刑期上限(月)
     */
    @Column(name = "penalty_term_max")
    private Integer penaltyTermMax;

    /**
     * 是否可缓刑 0-否 1-是
     */
    @Column(name = "is_can_probation")
    private Short isCanProbation;

    /**
     * 增减刑最小百分比
     */
    @Column(name = "deal_penalty_min_percent")
    private BigDecimal dealPenaltyMinPercent;

    /**
     * 增减刑最大百分比
     */
    @Column(name = "deal_penalty_max_percent")
    private BigDecimal dealPenaltyMaxPercent;

    /**
     * 酌情增减刑百分比
     */
    @Column(name = "appropriate_penalty_percent")
    private BigDecimal appropriatePenaltyPercent;

    /**
     * 犯罪地位,常量2034
     */
    @Column(name = "crimnal_position")
    private Integer crimnalPosition;

    /**
     * 判处情,常量况2035
     */
    @Column(name = "sentence_result")
    private Integer sentenceResult;

    /**
     * 主刑种类,常量2038
     */
    @Column(name = "principal_punishment_type")
    private Integer principalPunishmentType;

    /**
     * 缓刑种类,常量2039
     */
    @Column(name = "probation_type")
    private Integer probationType;

    /**
     * 缓刑刑期上限
     */
    @Column(name = "probation_term_max")
    private Integer probationTermMax;

    /**
     * 缓刑刑期下限
     */
    @Column(name = "probation_term_min")
    private Integer probationTermMin;

    /**
     * 剥夺政治权利下限
     */
    @Column(name = "deprivation_pr_term_min")
    private Integer deprivationPrTermMin;

    /**
     * 剥夺政治权利上限
     */
    @Column(name = "deprivation_pr_term_max")
    private Integer deprivationPrTermMax;

    /**
     * 罚金数额
     */
    @Column(name = "fine_amount")
    private BigDecimal fineAmount;

    /**
     * 没收个人财产金额
     */
    @Column(name = "confiscation_amount")
    private BigDecimal confiscationAmount;

    /**
     * 适用刑期种类
     */
    @Column(name = "suitable_punishment_type")
    private String suitablePunishmentType;

    /**
     * 适用刑期种类描述
     */
    @Column(name = "suitable_punishment_desc")
    private String suitablePunishmentDesc;

    /**
     * 判决结果内容
     */
    @Column(name = "judgment_result")
    private String judgmentResult;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取案件编号
     *
     * @return case_no - 案件编号
     */
    public Long getCaseNo() {
        return caseNo;
    }

    /**
     * 设置案件编号
     *
     * @param caseNo 案件编号
     */
    public void setCaseNo(Long caseNo) {
        this.caseNo = caseNo;
    }

    /**
     * 获取案件涉及被告姓名
     *
     * @return defendant_name - 案件涉及被告姓名
     */
    public String getDefendantName() {
        return defendantName;
    }

    /**
     * 设置案件涉及被告姓名
     *
     * @param defendantName 案件涉及被告姓名
     */
    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    /**
     * 获取案件涉及被告人标识
     *
     * @return defendant_id - 案件涉及被告人标识
     */
    public String getDefendantId() {
        return defendantId;
    }

    /**
     * 设置案件涉及被告人标识
     *
     * @param defendantId 案件涉及被告人标识
     */
    public void setDefendantId(String defendantId) {
        this.defendantId = defendantId;
    }

    /**
     * 获取案件涉及被告人证件类别
     *
     * @return id_type - 案件涉及被告人证件类别
     */
    public Integer getIdType() {
        return idType;
    }

    /**
     * 设置案件涉及被告人证件类别
     *
     * @param idType 案件涉及被告人证件类别
     */
    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    /**
     * 获取基础刑期下限(月)
     *
     * @return penalty_term_min - 基础刑期下限(月)
     */
    public Integer getPenaltyTermMin() {
        return penaltyTermMin;
    }

    /**
     * 设置基础刑期下限(月)
     *
     * @param penaltyTermMin 基础刑期下限(月)
     */
    public void setPenaltyTermMin(Integer penaltyTermMin) {
        this.penaltyTermMin = penaltyTermMin;
    }

    /**
     * 获取基础刑期上限(月)
     *
     * @return penalty_term_max - 基础刑期上限(月)
     */
    public Integer getPenaltyTermMax() {
        return penaltyTermMax;
    }

    /**
     * 设置基础刑期上限(月)
     *
     * @param penaltyTermMax 基础刑期上限(月)
     */
    public void setPenaltyTermMax(Integer penaltyTermMax) {
        this.penaltyTermMax = penaltyTermMax;
    }

    /**
     * 获取是否可缓刑 0-否 1-是
     *
     * @return is_can_probation - 是否可缓刑 0-否 1-是
     */
    public Short getIsCanProbation() {
        return isCanProbation;
    }

    /**
     * 设置是否可缓刑 0-否 1-是
     *
     * @param isCanProbation 是否可缓刑 0-否 1-是
     */
    public void setIsCanProbation(Short isCanProbation) {
        this.isCanProbation = isCanProbation;
    }

    public BigDecimal getDealPenaltyMinPercent() {
        return dealPenaltyMinPercent;
    }

    public void setDealPenaltyMinPercent(BigDecimal dealPenaltyMinPercent) {
        this.dealPenaltyMinPercent = dealPenaltyMinPercent;
    }

    public BigDecimal getDealPenaltyMaxPercent() {
        return dealPenaltyMaxPercent;
    }

    public void setDealPenaltyMaxPercent(BigDecimal dealPenaltyMaxPercent) {
        this.dealPenaltyMaxPercent = dealPenaltyMaxPercent;
    }

    /**
     * 获取酌情增减刑百分比
     *
     * @return appropriate_penalty_percent - 酌情增减刑百分比
     */
    public BigDecimal getAppropriatePenaltyPercent() {
        return appropriatePenaltyPercent;
    }

    /**
     * 设置酌情增减刑百分比
     *
     * @param appropriatePenaltyPercent 酌情增减刑百分比
     */
    public void setAppropriatePenaltyPercent(BigDecimal appropriatePenaltyPercent) {
        this.appropriatePenaltyPercent = appropriatePenaltyPercent;
    }

    /**
     * 获取犯罪地位,常量2034
     *
     * @return crimnal_position - 犯罪地位,常量2034
     */
    public Integer getCrimnalPosition() {
        return crimnalPosition;
    }

    /**
     * 设置犯罪地位,常量2034
     *
     * @param crimnalPosition 犯罪地位,常量2034
     */
    public void setCrimnalPosition(Integer crimnalPosition) {
        this.crimnalPosition = crimnalPosition;
    }

    /**
     * 获取判处情,常量况2035
     *
     * @return sentence_result - 判处情,常量况2035
     */
    public Integer getSentenceResult() {
        return sentenceResult;
    }

    /**
     * 设置判处情,常量况2035
     *
     * @param sentenceResult 判处情,常量况2035
     */
    public void setSentenceResult(Integer sentenceResult) {
        this.sentenceResult = sentenceResult;
    }

    /**
     * 获取主刑种类,常量2038
     *
     * @return principal_punishment_type - 主刑种类,常量2038
     */
    public Integer getPrincipalPunishmentType() {
        return principalPunishmentType;
    }

    /**
     * 设置主刑种类,常量2038
     *
     * @param principalPunishmentType 主刑种类,常量2038
     */
    public void setPrincipalPunishmentType(Integer principalPunishmentType) {
        this.principalPunishmentType = principalPunishmentType;
    }

    /**
     * 获取缓刑种类,常量2039
     *
     * @return probation_type - 缓刑种类,常量2039
     */
    public Integer getProbationType() {
        return probationType;
    }

    /**
     * 设置缓刑种类,常量2039
     *
     * @param probationType 缓刑种类,常量2039
     */
    public void setProbationType(Integer probationType) {
        this.probationType = probationType;
    }

    /**
     * 获取缓刑刑期上限
     *
     * @return probation_term_max - 缓刑刑期上限
     */
    public Integer getProbationTermMax() {
        return probationTermMax;
    }

    /**
     * 设置缓刑刑期上限
     *
     * @param probationTermMax 缓刑刑期上限
     */
    public void setProbationTermMax(Integer probationTermMax) {
        this.probationTermMax = probationTermMax;
    }

    /**
     * 获取缓刑刑期下限
     *
     * @return probation_term_min - 缓刑刑期下限
     */
    public Integer getProbationTermMin() {
        return probationTermMin;
    }

    /**
     * 设置缓刑刑期下限
     *
     * @param probationTermMin 缓刑刑期下限
     */
    public void setProbationTermMin(Integer probationTermMin) {
        this.probationTermMin = probationTermMin;
    }

    /**
     * 获取剥夺政治权利下限
     *
     * @return deprivation_pr_term_min - 剥夺政治权利下限
     */
    public Integer getDeprivationPrTermMin() {
        return deprivationPrTermMin;
    }

    /**
     * 设置剥夺政治权利下限
     *
     * @param deprivationPrTermMin 剥夺政治权利下限
     */
    public void setDeprivationPrTermMin(Integer deprivationPrTermMin) {
        this.deprivationPrTermMin = deprivationPrTermMin;
    }

    /**
     * 获取剥夺政治权利上限
     *
     * @return deprivation_pr_term_max - 剥夺政治权利上限
     */
    public Integer getDeprivationPrTermMax() {
        return deprivationPrTermMax;
    }

    /**
     * 设置剥夺政治权利上限
     *
     * @param deprivationPrTermMax 剥夺政治权利上限
     */
    public void setDeprivationPrTermMax(Integer deprivationPrTermMax) {
        this.deprivationPrTermMax = deprivationPrTermMax;
    }

    /**
     * 获取罚金数额
     *
     * @return fine_amount - 罚金数额
     */
    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    /**
     * 设置罚金数额
     *
     * @param fineAmount 罚金数额
     */
    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    /**
     * 获取没收个人财产金额
     *
     * @return confiscation_amount - 没收个人财产金额
     */
    public BigDecimal getConfiscationAmount() {
        return confiscationAmount;
    }

    /**
     * 设置没收个人财产金额
     *
     * @param confiscationAmount 没收个人财产金额
     */
    public void setConfiscationAmount(BigDecimal confiscationAmount) {
        this.confiscationAmount = confiscationAmount;
    }

    /**
     * 获取适用刑期种类
     *
     * @return suitable_punishment_type - 适用刑期种类
     */
    public String getSuitablePunishmentType() {
        return suitablePunishmentType;
    }

    /**
     * 设置适用刑期种类
     *
     * @param suitablePunishmentType 适用刑期种类
     */
    public void setSuitablePunishmentType(String suitablePunishmentType) {
        this.suitablePunishmentType = suitablePunishmentType;
    }

    /**
     * 获取适用刑期种类描述
     *
     * @return suitable_punishment_desc - 适用刑期种类描述
     */
    public String getSuitablePunishmentDesc() {
        return suitablePunishmentDesc;
    }

    /**
     * 设置适用刑期种类描述
     *
     * @param suitablePunishmentDesc 适用刑期种类描述
     */
    public void setSuitablePunishmentDesc(String suitablePunishmentDesc) {
        this.suitablePunishmentDesc = suitablePunishmentDesc;
    }

    /**
     * 获取判决结果内容
     *
     * @return judgment_result - 判决结果内容
     */
    public String getJudgmentResult() {
        return judgmentResult;
    }

    /**
     * 设置判决结果内容
     *
     * @param judgmentResult 判决结果内容
     */
    public void setJudgmentResult(String judgmentResult) {
        this.judgmentResult = judgmentResult;
    }
}