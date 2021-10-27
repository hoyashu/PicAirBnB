package domain;

public class MemberVo {
	private int memNo;
	private String id;
	private String pwd;
	private String name;
	private String nick;
	private String gender;
	private String hp;
	private String birth;
	private String joinDate;
	private int state;
	private int boardCount;
	private int commentCount;
	private int visitCount;
	private int grade;

	public MemberVo() {
		super();

	}

	public MemberVo(String id, String nick, String joinDate) {
		super();
		this.id = id;
		this.nick = nick;
		this.joinDate = joinDate;
	}

	public MemberVo(int memNo, String nick, String gender, String joinDate, int boardCount, int commentCount,
			int visitCount, int grade) {
		super();
		this.memNo = memNo;
		this.nick = nick;
		this.gender = gender;
		this.joinDate = joinDate;
		this.boardCount = boardCount;
		this.commentCount = commentCount;
		this.visitCount = visitCount;
		this.grade = grade;
	}

	public MemberVo(int memNo, String id, String pwd, String name, String nick, String gender, String hp, String birth,
			String joinDate, int state, int boardCount, int commentCount, int visitCount, int grade) {
		super();
		this.memNo = memNo;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nick = nick;
		this.gender = gender;
		this.hp = hp;
		this.birth = birth;
		this.joinDate = joinDate;
		this.state = state;
		this.boardCount = boardCount;
		this.commentCount = commentCount;
		this.visitCount = visitCount;
		this.grade = grade;
	}

	public MemberVo(int memNo, String id, String pwd, String name, String nick, String gender, String hp,
			String birth) {
		super();
		this.memNo = memNo;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nick = nick;
		this.gender = gender;
		this.hp = hp;
		this.birth = birth;
	}

	public MemberVo(String id, String pwd, String name, String nick, String gender, String hp, String birth) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nick = nick;
		this.gender = gender;
		this.hp = hp;
		this.birth = birth;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}