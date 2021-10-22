package controller;
//받아온 경로를 redirect or forward중에 뭐로 실행시킬건지에 대해 저장함
public class ActionForward {

	private String path;
	private boolean isRedirect; //redirect or forward

	public ActionForward() {
		super();
	}

	public ActionForward(String path, boolean isRedirect) {
		super();
		this.path = path;
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	

}
