
public abstract class Account {
	protected String id;
	protected String password;
	protected String type;
	protected String name;
	protected static int numAccounts;
	
	Account(){}

	Account(String info){
		System.out.println("I WAS AT ACCOUNT");
		String[] elements = info.split("\\|");
		this.id = elements[0];
		this.password = elements[1];
		this.name = elements[2];
		this.type = elements[3];
		if (Account.numAccounts < Integer.parseInt(this.id)) {
			Account.numAccounts = Integer.parseInt(this.id);
		}
	}
	
	Account (String id, String password, String name , String type){
		this.id = id;
		this.password = password;
		this.name = name;
		this.type = type;
		if (Account.numAccounts < Integer.parseInt(this.id)) {
			Account.numAccounts = Integer.parseInt(this.id);
		}
	}
	
	public boolean login(String id, String password, String type) {
		if (this.id.equals(id) 
			&& this.password.equals(password) 
			&& this.type.equals(type)) {
			return true;
		}
		return false;
	}
	
	public abstract void showGUIOptions();
	
	protected void changePassword(String oldPassword, String newPassword) {
		if (oldPassword.equals(this.password)) {
			this.password = newPassword;
		} 
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}
	
	public String toText() {
		return id+"|"+password+"|"+name+"|"+type;
	}

	public String toString() {
		return "ID:"+id+"\nName:"+name+"\nType:"+type;
	}

	public Object getName() {
		return this.name;
	}

}
