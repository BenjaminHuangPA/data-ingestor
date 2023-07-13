import java.io.IOException;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class predictSpringProjectAPITesting {
	public static void main(String[] args) {
		predictSpringProjectAPI p = new predictSpringProjectAPI(8983, "predictspringproject");
		try {
			SearchResult[] s = p.getMostSoldProducts();
			for(SearchResult sr : s) {
				System.out.println(sr.getQuantitySold());
				System.out.println(sr.getTitle());
			}
		} catch (IOException e) {
			System.out.println("There was an IOException");
			System.out.println(e);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			System.out.println("There was a SolrServerException");
			System.out.println(e);
		}
	}
}
