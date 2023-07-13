import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.solr.common.SolrDocument;

public class SearchResult {
	
	private SolrDocument s;
	private String sku_id;
	private String image;
	private String additional_image_link;
	private String style_id;
	private String class_id;
	private String color;
	private String color_code;
	private String color_family;
	private String size;
	private String size_id;
	private int department_id;
	private String dissection_code;
	private boolean hazmat;
	private boolean redline;
	private boolean promoted;
	private String tax_code;
	private String vendor;
	private float list_price;
	private float sale_price;
	private Date sale_price_effective_date;
	private String currency;
	private boolean shoprunner_eligible;
	private String title;
	private String link;
	private String prod_description;
	private Date start_date;
	private boolean featured_color;
	private boolean[] featured_color_category;
	private String[] related_products;
	private boolean pre_order;
	private String handling_code;
	private String video;
	private String proportion;
	private String proportion_products;
	private String master_style;
	private boolean cannot_return;
	private boolean great_find;
	private boolean web_exclusive;
	private boolean ny_deals;
	private String promo_tagline;
	private boolean partially_promoted;
	private String product_category;
	private int[] sort_order;
	private int quantity_sold;
	private float average_rating;
	
	public String getSkuId() {
		return this.sku_id;
	}
	
	public String getImage() {
		return this.image;
	};
	
	
	public String getAdditionalImageLink() {
		return this.additional_image_link;
	}
	
	public String getStyleId() {
		return this.style_id;
	}
	
	public String getClassId() {
		return this.class_id;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public String getColorCode() {
		return this.color_code;
	}
	
	
	public String getColorFamily() {
		return this.color_family;
	}
	
	public String getSize() {
		return this.size;
	}
	
	public String getSizeId() {
		return this.size_id;
	}
	
	
	public int getDepartmentId() {
		return this.department_id;
	}
	
	public String getDissectionCode() {
		return this.dissection_code;
	}
	
	public boolean getHazmat() {
		return this.hazmat;
	}
	
	public boolean getRedline() {
		return this.redline;
	}
	
	public boolean getPromoted() {
		return this.promoted;
	}
	
	public String getTaxCode() {
		return this.tax_code;
	}
	
	public String getVendor() {
		return this.vendor;
	}
	
	public float getListPrice() {
		return this.list_price;
	}
	
	public float getSalePrice() {
		return this.sale_price;
	}
	
	public Date getSalePriceEffectiveDate() {
		return this.sale_price_effective_date;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public boolean getShoprunnerEligible() {
		return this.shoprunner_eligible;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public String getProdDescription() {
		return this.prod_description;
	}
	
	public Date getStartDate() {
		return this.start_date;
	}
	
	public boolean getFeaturedColor() {
		return this.featured_color;
	}
	
	public boolean[] getFeaturedColorCategory() {
		return this.featured_color_category;
	}
	
	public String[] getRelatedProducts() {
		return this.related_products;
	}
	
	public boolean getPreOrder() {
		return this.pre_order;
	}
	
	public String getHandlingCode() {
		return this.handling_code;
	}
	
	public String getVideo() {
		return this.video;
	}
	
	public String getProportion() {
		return this.proportion;
	}
	
	public String getProportionProducts() {
		return this.proportion_products;
	}
	
	public String getMasterStyle() {
		return this.master_style;
	}
	
	public boolean getCannotReturn() {
		return this.cannot_return;
	}
	
	public boolean getGreatFind() {
		return this.great_find;
	}
	
	public boolean getWebExclusive() {
		return this.web_exclusive;
	}
	
	public boolean getNyDeals() {
		return this.ny_deals;
	}
	
	public String getPromoTagline() {
		return this.promo_tagline;
	}
	
	public boolean getPartiallyPromoted() {
		return this.partially_promoted;
	}
	
	public String getProductCategory() {
		return this.product_category;
	}
	
	public int[] getSortOrder() {
		return this.sort_order;
	}
	
	public int getQuantitySold() {
		return this.quantity_sold;
	}
	
	public float getAverageRating() {
		return this.average_rating;
	}
	
	public SearchResult(SolrDocument s) {
		this.s = s;
		this.sku_id = (String) s.get("sku_id");
		this.image = (String) s.get("image");
		this.additional_image_link = (String) s.get("additional_image_link");
		this.style_id = (String) s.get("style_id");
		this.class_id = (String) s.get("style_id");
		this.color = (String) s.get("color");
		this.color_code = (String) s.get("color_code");
		this.color_family = (String) s.get("color_family");
		this.size = (String) s.get("size");
		this.size_id = (String) s.get("size_id");
		this.department_id = (int) s.get("department_id");
		this.dissection_code = (String) s.get("dissection_code");
		this.hazmat = (boolean) s.get("hazmat");
		this.redline = (boolean) s.get("redline");
		this.promoted = (boolean) s.get("promoted");
		this.tax_code = (String) s.get("tax_code");
		this.vendor = (String) s.get("vendor");
		this.list_price = (float) s.get("list_price");
		this.sale_price = (float) s.get("sale_price");
		
		String sale_price_effective_date = (String) s.get("sale_price_effective_date");
		if(sale_price_effective_date.equals("")) {
			this.sale_price_effective_date = null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				this.sale_price_effective_date = sdf.parse(sale_price_effective_date);
			} catch (ParseException e) {
				this.sale_price_effective_date = null;
				System.out.println("There was an error parsing the date.");
			}
		}
		this.currency = (String) s.get("currency");
		this.shoprunner_eligible = (boolean) s.get("shoprunner_eligible");
		this.title = (String) s.get("title");
		this.link = (String) s.get("link");
		this.prod_description = (String) s.get("prod_description");
		
		String start_date = (String) s.get("sale_price_effective_date");
		if(start_date.equals("")) {
			this.start_date = null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				this.start_date = sdf.parse(start_date);
			} catch (ParseException e) {
				this.start_date = null;
				System.out.println("There was an error parsing the date.");
			}
		}
		
		this.featured_color = (boolean) s.get("featured_color");
		
		String featured_color_category = (String) s.get("featured_color_category");
		String[] featured_color_category_split = featured_color_category.split(",");
		this.featured_color_category = new boolean[featured_color_category_split.length];
		for(int i = 0; i < featured_color_category_split.length; i++) {
			this.featured_color_category[i] = Boolean.parseBoolean(featured_color_category_split[i]);
		}
		
		String related_products = (String) s.get("related_products");
		this.related_products = related_products.split(",");
		
		this.pre_order = (boolean) s.get("pre_order");
		this.handling_code = (String) s.get("handling_code");
		this.video = (String) s.get("video");
		this.proportion = (String) s.get("proportion");
		this.proportion_products = (String) s.get("proportion_products");
		this.master_style = (String) s.get("master_style");
		this.cannot_return = (boolean) s.get("cannot_return");
		this.great_find = (boolean) s.get("cannot_return");
		this.web_exclusive = (boolean) s.get("cannot_return");
		this.ny_deals = (boolean) s.get("cannot_return");
		this.promo_tagline = (String) s.get("promo_tagline");
		this.partially_promoted = (boolean) s.get("partially_promoted");
		this.product_category = (String) s.get("product_category");
		
		String sort_order = (String) s.get("sort_order");
		String[] sort_order_split = sort_order.split(",");
		this.sort_order = new int[sort_order_split.length];
		for(int i = 0; i < sort_order_split.length; i++) {
			this.sort_order[i] = Integer.parseInt(sort_order_split[i]);
		}
		
		this.quantity_sold = (int) s.get("quantity_sold");
		this.average_rating = (float) s.get("average_rating");
		
		
	}
}
