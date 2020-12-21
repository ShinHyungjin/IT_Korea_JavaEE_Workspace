package common.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Pager {
	private int totalRecord=0; // 총 레코드 수
	private int pageSize = 10; //페이지 당 보여질 레코드 수
	private int totalPage; // 총 존재해야 할 페이지
	private int blockSize = 10; //블럭당 보여질 페이지 수
	private int currentPage = 1; // 현재 페이지
	private int firstPage;
	private int lastPage;
	private int curPos;
	private int num;
	
	//선언된 변수 초기화
	//실시간으로 게시글 수, 현재 페이지 등의 정보가 바뀌기 때문에 해당 데이터는 아래에서 선언 및 조합해야함
	public void init(HttpServletRequest request, List list) {
		totalRecord = list.size();
		totalPage = (int)Math.ceil((float)totalRecord/pageSize); // 26개 레코드라면 2.6개의 페이지인데 이는 3개가 되야하므로 올림연산 후 형변환
		//페이지를 선택한 경우, 그 선택된 페이지로 대체
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = currentPage - (currentPage-1)%blockSize; // 현재 페이지가 4라면, 4 - (4-1)%10 = 1이 나온다 즉 1~10은 1이 나오는 구조 
		lastPage = firstPage + (blockSize-1); // 위에서 구한 첫 페이지에 9를 더해줌
		curPos = (currentPage-1) * pageSize; //페이지당 List내에서의 시작 Index
		num = totalRecord - curPos; //페이지당 시작 번호
		
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurPos() {
		return curPos;
	}

	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
