package uet.chatapp.models;

import java.util.HashMap;
import java.util.List;

import uet.chatapp.chatapp.R;

public class Variables {

	public static final String[] PAPERS = {"24h", "Vietnamnet",};
	public static final int[] ICONS = {R.drawable.hn24h,R.drawable.vietnamnet};
	
	
	public static final String[] HN24H_CATEGORIES = {"Tin tức trong ngày", "Bóng đá", "An ninh - Hình sự",
		"Th�?i trang", "Th�?i trang Hi-tech", "Tài chính - Bất động sản", "Ẩm thực", "Làm đẹp", "Phim",
		"Giáo dục - Du h�?c", "Bạn trẻ cuộc sống", "Ca nhạc - MTV", "Thể thao", "Phi trư�?ng - kỳ quặc",
		"Công nghệ thông tin", "Ô tô - Xe máy", "Thị trư�?ng - Tiêu dùng",
		"Du lịch", "Súc kh�?e đ�?i sống", "Cư�?i 24h"};
	public static final String[] HN24H_LINKS = {"http://www.24h.com.vn/upload/rss/tintuctrongngay.rss",
		"http://www.24h.com.vn/upload/rss/bongda.rss",
		"http://www.24h.com.vn/upload/rss/anninhhinhsu.rss",
		"http://www.24h.com.vn/upload/rss/thoitrang.rss",
		"http://www.24h.com.vn/upload/rss/thoitranghitech.rss",
		"http://www.24h.com.vn/upload/rss/taichinhbatdongsan.rss",
		"http://www.24h.com.vn/upload/rss/amthuc.rss",
		"http://www.24h.com.vn/upload/rss/lamdep.rss",
		"http://www.24h.com.vn/upload/rss/phim.rss",
		"http://www.24h.com.vn/upload/rss/giaoducduhoc.rss",
		"http://www.24h.com.vn/upload/rss/bantrecuocsong.rss",
		"http://www.24h.com.vn/upload/rss/canhacmtv.rss",
		"http://www.24h.com.vn/upload/rss/thethao.rss",
		"http://www.24h.com.vn/upload/rss/phithuongkyquac.rss",
		"http://www.24h.com.vn/upload/rss/congnghethongtin.rss",
		"http://www.24h.com.vn/upload/rss/otoxemay.rss",
		"http://www.24h.com.vn/upload/rss/thitruongtieudung.rss",
		"http://www.24h.com.vn/upload/rss/dulich.rss",
		"http://www.24h.com.vn/upload/rss/suckhoedoisong.rss",
		"http://www.24h.com.vn/upload/rss/cuoi24h.rss"};
	
	
	//Vietnamnet
		public static final String[] VIETNAMNET_CATEGORIES = {"Chính trị", "Xã hội", "Quốc tế", "Giáo dục",
			"Kinh tế", "Văn hóa", "Thể thao", "�?�?i sống", "CNTT - Viễn thông", "Khoa h�?c","Bạn đ�?c",
			"Ô tô - Xe máy", "An toàn giao thông", "Bảo vệ ngư�?i tiêu dùng", "Chia sẻ", "Du lịch"};
		public static final String[] VIETNAMNET_LINKS = {"http://vietnamnet.vn/rss/chinh-tri.rss",
			"http://vietnamnet.vn/rss/xa-hoi.rss",
			"http://vietnamnet.vn/rss/quoc-te.rss",
			"http://vietnamnet.vn/rss/giao-duc.rss",
			"http://vietnamnet.vn/rss/kinh-te.rss",
			"http://vietnamnet.vn/rss/van-hoa.rss",
			"http://vietnamnet.vn/rss/the-thao.rss",
			"http://vietnamnet.vn/rss/doi-song.rss",
			"http://vietnamnet.vn/rss/cong-nghe-thong-tin-vien-thong.rss",
			"http://vietnamnet.vn/rss/khoa-hoc.rss",
			"http://vietnamnet.vn/rss/ban-doc-phap-luat.rss",
			"http://vietnamnet.vn/rss/chuyen-trang/oto-xe-may.rss",
			"http://vietnamnet.vn/rss/xa-hoi/an-toan-giao-thong.rss",
			"http://vietnamnet.vn/rss/bao-ve-nguoi-tieu-dung.rss",
			"http://vietnamnet.vn/rss/ban-doc-phap-luat/chia-se.rss",
			"http://vietnamnet.vn/rss/doi-song/du-lich.rss"};
	
	
	//All
	public static final String[][] CATEGORIES = { HN24H_CATEGORIES,  VIETNAMNET_CATEGORIES};
	public static final String[][] LINKS = { HN24H_LINKS,  VIETNAMNET_LINKS};
	
	public static final String paper = "paper";
	public static final String category = "category";
	public static final String key = "key";
	public static final String position = "position";
	
	public static HashMap<Integer, List<RssItem>> MAP = new HashMap<Integer, List<RssItem>>();
}
