import java.io.*;
import java.net.*;

public class WordCounter {
 
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException {
        try {
            URL url = new URL(this.urlStr);
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder retVal = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) {
                retVal.append(line).append("\n");
            }
            return retVal.toString();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + this.urlStr);
            throw e; // 重新拋出例外，使得上層能夠處理
        }
    }
    
    public int countKeyword(String keyword) throws IOException{
    	if (content == null)
    		content = fetchContent();
    	
  
    	//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
    	content = content.toUpperCase();
    	keyword = keyword.toUpperCase();
 
    	int retVal = 0;
    	int fromIdx = 0;
    	int found = -1;
 
    	while ((found = content.indexOf(keyword, fromIdx)) != -1){
    		retVal++;
    		fromIdx = found + keyword.length();
    	}
    	return retVal;
    }


}