package domain;

public class MemberVo {

	private int memberGrade; // 등급
	private String memberGradeName; // 등급명
	private boolean memberGradeType; // 등업 방식( 0 : 자동, 1 : 수동 )
	private int memberGradeBoard; // 등업기준_게시글수
	private int memberGradeComment; // 등업기준_댓글수
	private int memberGradeVisit; // 등업기준_방문일수
	private boolean memberGradeUse; // 등급사용여부( 0 : 미사용, 1 : 사용)
}
