package Tennis;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Record implements Serializable{
	
	
	private static final long serialVersionUID = -4128230566810992555L;
	
	private String fileName = ".\\";
	private ArrayList<Game> list = new ArrayList<>(); 
	
	
	public ArrayList<Game> getList() {
		return list;
	}

	public void setList(ArrayList<Game> list) {
		this.list = list;
	}

	public Record(String string) {
		fileName += string+".dat";
	}

	public void recordGame(Game game) {
		Game temp = null;
		if(game instanceof NoTieBreakGame)
			temp = (NoTieBreakGame)new NoTieBreakGame(game);
		else
			temp = (TieBreakGame)new TieBreakGame(game);
		list.add(temp);
	}
	
	public void Write() {
		try (FileOutputStream out = new FileOutputStream(fileName);
			 ObjectOutputStream oos = new ObjectOutputStream(out);
			){
				oos.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void End() {
		
	}
	
	public static Record Read(String fileName) {	
		try (FileInputStream in = new FileInputStream(fileName);
			 ObjectInputStream ois = new ObjectInputStream(in);
			){
			Record temp;
			temp = (Record) ois.readObject();
			return temp;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
