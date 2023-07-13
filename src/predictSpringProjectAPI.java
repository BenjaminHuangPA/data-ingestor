import java.io.IOException;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class predictSpringProjectAPI {
	
	private SolrClient client;
	private String SolrConnString;
	
	
	public SearchResult[] getProductsBySKU(String sku) throws IOException, SolrServerException {
		//get products by SKU
		//there may be multiple products with the same SKU in the database
		SolrQuery query = new SolrQuery("sku_id:" + sku);
		query.setStart(0);
		query.set("defType", "lucene");
		QueryResponse query_response = client.query(query);
		
		SolrDocumentList list = query_response.getResults();
		SearchResult[] sr = new SearchResult[list.size()];
		for(int i = 0; i < list.size(); i++) {
			sr[i] = new SearchResult(list.get(i));
		}
		
		return sr;
	}
	
	public SearchResult[] getHighestRatedProducts() throws IOException, SolrServerException {
		//get the highest rated products (10 of them)
		SolrQuery query = new SolrQuery("*:*");
		query.setStart(0);
		query.setSort("average_rating", ORDER.desc);
		query.set("defType", "lucene");
		QueryResponse query_response = client.query(query);
		SolrDocumentList list = query_response.getResults();
		
		SearchResult[] sr = new SearchResult[list.size()];
		for(int i = 0; i < list.size(); i++) {
			sr[i] = new SearchResult(list.get(i));
		}
		
		return sr;
	}
	
	public SearchResult[] getMostSoldProducts() throws IOException, SolrServerException {
		//get the most sold products
		SolrQuery query = new SolrQuery("*:*");
		query.setStart(0);
		query.setSort("quantity_sold", ORDER.desc);
		query.set("defType", "lucene");
		QueryResponse query_response = client.query(query);
		SolrDocumentList list = query_response.getResults();
		
		SearchResult[] sr = new SearchResult[list.size()];
		for(int i = 0; i < list.size(); i++) {
			sr[i] = new SearchResult(list.get(i));
		}
		
		return sr;
	}
	
	public SearchResult[] getItemsBySalePriceRange(int lower_price, int higher_price) throws IOException, SolrServerException {
		//get products whose sale price is between a user-provided range
		SolrQuery query = new SolrQuery("*:*");
		query.addFilterQuery("sale_price:[" + lower_price + " TO " + higher_price + "]");
		query.setStart(0);
		
		query.set("defType", "lucene");
		QueryResponse query_response = client.query(query);
		
		SolrDocumentList list = query_response.getResults();
		SearchResult[] sr = new SearchResult[list.size()];
		for(int i = 0; i < list.size(); i++) {
			sr[i] = new SearchResult(list.get(i));
		}
		
		return sr;
	}
	
	public predictSpringProjectAPI(int port, String db_name) {
		this.SolrConnString = "http://localhost:" + port + "/solr/" + db_name;
		this.client = new HttpSolrClient.Builder(this.SolrConnString).build();
	}
	
	
	
	
}
