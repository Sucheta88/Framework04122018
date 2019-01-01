package commonLibs.contracts;

public interface AlertHandler {

	public void acceptALert() throws Exception;

	public void rejectALert() throws Exception;

	public String getMessageFromAlert() throws Exception;

}
