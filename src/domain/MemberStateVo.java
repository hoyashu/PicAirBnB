package domain;

public class MemberStateVo {
	private int stateNo;
	private int memNo;
	private String reason;
	private String withdrawDate;
	
	public MemberStateVo() {
		super();
	}

	public MemberStateVo(int stateNo, int memNo, String reason, String withdrawDate) {
		super();
		this.stateNo = stateNo;
		this.memNo = memNo;
		this.reason = reason;
		this.withdrawDate = withdrawDate;
	}

	public int getStateNo() {
		return stateNo;
	}

	public void setStateNo(int stateNo) {
		this.stateNo = stateNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(String withdrawDate) {
		this.withdrawDate = withdrawDate;
	}
	
}
