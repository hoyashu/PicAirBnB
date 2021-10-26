package model.service;

import java.util.ArrayList;

import domain.RoomVo;
import model.dao.board.RoomDao;

public class RoomService {
	// single pattern
	private static RoomService service;

	private RoomService() {

	}

	public static RoomService getInstance() {
		if (service == null) {
			service = new RoomService();
		}
		return service;
	}

	// 숙소 목록을 조회하다.
	public ArrayList<RoomVo> retrieveRoomList() throws Exception {
		RoomDao roomDao = RoomDao.getInstance();
		
		return roomDao.selectRoomList();
	}

}
