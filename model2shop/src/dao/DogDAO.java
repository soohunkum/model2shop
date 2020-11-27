package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Dog;

public class DogDAO {
	
		Connection con;
		private static DogDAO boardDAO;
		
		private DogDAO() {
			
		}
		
		public void setConnection(Connection con) {
			this.con = con;
		}
		
		public static DogDAO getInstance() {
			if(boardDAO == null) {
				boardDAO = new DogDAO();
			}
			return boardDAO;
		}
		
		public ArrayList<Dog> selectDogList(){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<Dog> dogList = null;
			try {
				pstmt = con.prepareStatement("select * from dog");
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dogList = new ArrayList<Dog>();
					
					do {
						dogList.add(new Dog(rs.getInt("id")
								,rs.getString("kind")
								,rs.getInt("price")
								,rs.getString("image")
								,rs.getString("country")
								,rs.getInt("height")
								,rs.getInt("weight")
								,rs.getString("content")
								,rs.getInt("readcount")));
					}while(rs.next());
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return dogList;
		}
}
