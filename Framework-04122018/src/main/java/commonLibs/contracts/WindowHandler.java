package commonLibs.contracts;

import java.util.Set;

public interface WindowHandler {

	public void switchToAnyWindow(String windowHandle) throws Exception;

	public void switchToAnyWindow(int childWindowIndex) throws Exception;

	public String getWindowHandle(String windowHandle) throws Exception;

	public Set<String> getWindowHandles() throws Exception;

}
