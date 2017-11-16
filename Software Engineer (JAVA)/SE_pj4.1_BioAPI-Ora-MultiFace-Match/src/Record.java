

public class Record {
	private int Id;
	private String name;
	private byte[] tempalte;
	
	public void setId(int id) {
		Id = id;
	}
	public int getId() {
		return Id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setTempalte(byte[] tempalte) {
		this.tempalte = tempalte;
	}
	public byte[] getTempalte() {
		return tempalte;
	}
}
