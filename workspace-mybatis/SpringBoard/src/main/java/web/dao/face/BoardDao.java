package web.dao.face;

import java.util.List;

import web.dto.Board;
import web.dto.Paging;

public interface BoardDao {

	public List<Board> selectAll(Paging paging);

	public int selectCntAll();

}