package com.knongdai.tinh.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.knongdai.tinh.entities.ProductTemperory;
import com.knongdai.tinh.entities.SourceCategory;
import com.knongdai.tinh.entities.SubTwoCategory;
import com.knongdai.tinh.services.ProductTmpService;
import com.knongdai.tinh.services.SourceCategoryService;

@Controller
@EnableScheduling
public class ProductScrapping {
	@Autowired
	private ProductTmpService pts;
	
	@Autowired 
	private SourceCategoryService scs;

	/**
	 * Scrap data auto by set fixed delay
	 */
	@Scheduled(fixedDelay = 30*60000)
	private void scrap(){
		System.out.println("Autoscrap...");
		
		Pattern pattern = Pattern.compile(".*'([^']*)'.*");
		ArrayList<SourceCategory> lists = scs.getAllUrlToScrap();
		for(SourceCategory s : lists)
		{
				try {
					List<ProductTemperory> productstmp = new ArrayList<>();
					ProductTemperory p = new ProductTemperory();
					SubTwoCategory s2 = new SubTwoCategory();
					Document doc =Jsoup.connect(s.getSourcecategory()).timeout(9000).userAgent("Mozila").get();
					Elements els = doc.select(s.getSource().getRowsselector());
					for(Element el : els){
						String title = el.select(s.getSource().getTitleselector()).text();
						String image = el.select(s.getSource().getImageselector()).attr(s.getSource().getImageattribute());
						String price = el.select(s.getSource().getPriceselector()).text();
						String url = el.select(s.getSource().getLinkselector()).attr("href");
						
						Document detailDocuments = Jsoup.connect(s.getSource().getPrefixlink()+url).timeout(9000).userAgent("Mozila").get();
						String description = detailDocuments.select(s.getSource().getDescription()).text();				
						Elements multiImgELements = detailDocuments.select(s.getSource().getMultiImageRowSelector());
						StringBuffer multiImages = new StringBuffer();
						for(Element e : multiImgELements){
							multiImages.append(
									s.getSource().getPrefiximage()+
									e.select(s.getSource().getMultiImageSelector()).attr(s.getSource().getMultiImageAttribute())+ " " );
						}
						multiImages.append(s.getSource().getPrefiximage()+image+" ");
						String images = new String(multiImages);
						Matcher matcher = pattern.matcher(images);
						String getPrice = "0";
						if(!price.trim().equals("") ){
								 getPrice = price.replace("$","");
								 getPrice = getPrice.replace(",","");
								 getPrice = getPrice.replace("USD","");
						}
						
						if(matcher.matches()) {
							images=matcher.group(1);
					    }
						p.setUrl(url);
						p.setImage(image);
						p.setImages(images);
						p.setTitle(title);
						p.setPrice(Double.parseDouble(getPrice));
						p.setDescription(description);
						s.getSourcecategoryid();
						p.setSourceCategory(s);
						s2.setSubtwocategoryid(s.getSubCategory().getSubtwocategoryid());
						p.setSubTwoCategory(s2);
						productstmp.add(p);
						
						System.out.println(">" + s.getSource().getPrefiximage() + p.getImage());
						System.out.println(">lst" + s.getSource().getPrefiximage() + p.getImages());
						
						pts.saveProductTmp(productstmp);	
					}
				} catch (IOException e){
					System.err.println("error"+ e.getMessage());
				}
			}
		}
	}
		
	
//	@Scheduled(cron="0 0 * * * *") // Work every one hour
	/*@Scheduled(fixedDelay = 6000)
	private void startScrapProduct(){  		
		
		Pattern pattern = Pattern.compile(".*'([^']*)'.*");
		ArrayList<SourceCategory> lists = scs.getAllUrlToScrap();
		for(SourceCategory s : lists)
		{		
			if(s.isStatus() == true)
			{
				try {
					List<Product> products = new ArrayList<>();
					Product p = new Product();
					SubTwoCategory s2 = new SubTwoCategory();
					Document doc = Jsoup.connect(s.getSourcecategory()).timeout(9000).userAgent("Mozila").get();
					Elements els = doc.select(s.getSource().getRowsselector());
					
					System.out.println(s.getSource().getMultiImageAttribute()+" // get multi-image");
					
					for(Element el : els){
						String title = el.select(s.getSource().getTitleselector()).text();
						String image = el.select(s.getSource().getImageselector()).attr(s.getSource().getImageattribute());
						String price = el.select(s.getSource().getPriceselector()).text();
						String url = el.select(s.getSource().getLinkselector()).attr("href");
						
			
						Document detailDocuments = Jsoup.connect(url).timeout(9000).userAgent("Mozila").get();
						String description = detailDocuments.select(s.getSource().getDescription()).text();				
						Elements multiImgELements = detailDocuments.select(s.getSource().getMultiImageRowSelector());
						ArrayList<String> listImage = new ArrayList<>();
			
						for(Element e : multiImgELements){
							listImage.add(e.select(s.getSource().getMultiImageSelector()).attr(s.getSource().getMultiImageAttribute()));
						}
												
						Matcher matcher = pattern.matcher(image);
						String getPrice = "0";
						System.out.println("=======>> "+price);
						System.out.println("=======>> "+url);
						if(!price.trim().equals("") ){
								 getPrice = price.replace("$","");
								 getPrice = getPrice.replace(",","");
								 getPrice = getPrice.replace("USD","");
								 //getPrice = price.replace("$","");
								 getPrice = getPrice.replace("៛","");
						}
						
						if(matcher.matches()) {
							image=matcher.group(1);
					    }
						
						p.setUrl(url);
						p.setImage(image);
						p.setTitle(title+" "+s.getSubCategory().getCategoryname());
						p.setPrice(Double.parseDouble(getPrice));
						p.setDescription(description);
						p.setMultiImage(listImage);
						s.getSourcecategoryid();
						p.setSourceCategory(s);
						s2.setSubtwocategoryid(s.getSubCategory().getSubtwocategoryid());
						p.setSubtwoCategory(s2);
					
						products.add(p);
						ps.addProducts(products);
					}
					
				} catch (IOException e) {
					System.err.println("error"+ e.getMessage());
				}
			}
			if(s.isStatus() == false)
			{
				try {
					List<ProductTemperory> productstmp = new ArrayList<>();
					ProductTemperory p = new ProductTemperory();
					SubTwoCategory s2 = new SubTwoCategory();
					Document doc =Jsoup.connect(s.getSourcecategory()).timeout(9000).userAgent("Mozila").get();
					Elements els = doc.select(s.getSource().getRowsselector());
					for(Element el : els){
						String title = el.select(s.getSource().getTitleselector()).text();
						String image = el.select(s.getSource().getImageselector()).attr(s.getSource().getImageattribute());
						String price = el.select(s.getSource().getPriceselector()).text();
						String url = el.select(s.getSource().getLinkselector()).attr("href");
						Matcher matcher = pattern.matcher(image);
						String getPrice = "0";
						if(!price.trim().equals("") ){
								 getPrice = price.replace("$","");
								 getPrice = getPrice.replace(",","");
								 getPrice = getPrice.replace("USD","");
						}
						
						if(matcher.matches()) {
							image=matcher.group(1);
					    }
						
						p.setUrl(url);
						p.setImage(image);
						p.setTitle(title);
						p.setPrice(Double.parseDouble(getPrice));
						s.getSourcecategoryid();
						p.setSourceCategory(s);
						s2.setSubtwocategoryid(s.getSubCategory().getSubtwocategoryid());
						p.setSubTwoCategory(s2);
						productstmp.add(p);
						pts.createProductTemperory(productstmp);			
					}
					
				} catch (IOException e){
					System.err.println("error"+ e.getMessage());
				}
			}	
		}
	}*/

