package com.huangzl.test.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.hibernate.search.store.RAMDirectoryProvider;


/**
 * 文件大于内存:IO流不会加载整个文件,每次读取指定的字节数.
 * 如果需要处理大于内存的整个文件,考虑使用RandomAccessFile使用seek分开读取
 * @author Administrator
 *
 */
public class FileBigThenMemory {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
//		logFile();
		readFile();
//		copy();
		
		
//		splitFile();//txt可以拆分合并,exe测试失败
//		mergeFile();
		
//		hash();
	}
	
	static void hash(){
//		String s = "http://my.51job.com/my/My_Pmc.php?1472328444";
		String s = "http://search.51job.com/list/co,c,2187839,000000,50,6.html";
		int hash = s.hashCode();
		System.out.println(hash);
		
		
	}
	
	static void readFile() throws Exception{

//		String filePath = "D:\\aa\\dc_zyk_czrk_rkxx.dmp";
//		String filePath = "D:\\aa\\txt.txt";
		String filePath = "D:\\aa\\log.log";
		File file6g = new File(filePath);
		
//		InputStream is = new FileInputStream(file6g);
		
		FileReader fr = new FileReader(file6g);
		
		BufferedReader br = new BufferedReader(fr);
		
		/*int i=0;
		while(i<10){
			i++;
			String line = br.readLine();
			System.out.println(line);
		}*/
		
		int j = 0;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String t = br.readLine();
		while(t != null){
			j++;
//			System.out.println(t);
			
			Integer count = map.get(t);
			if(count == null){
				map.put(t, 1);
			}else{
				map.put(t, map.get(t)+1);
			}
//			Integer hs = t.hashCode();
			
			t = br.readLine();
			
		}
		
		System.out.println(map);
		//{http://myhost/mypath/51=351296, http://myhost/mypath/50=351774, http://myhost/mypath/53=351362, http://myhost/mypath/52=350035, http://myhost/mypath/55=351904, http://myhost/mypath/54=351747, http://myhost/mypath/57=351707, http://myhost/mypath/56=351519, http://myhost/mypath/59=351714, http://myhost/mypath/58=351665, http://myhost/mypath/42=350467, http://myhost/mypath/41=352214, http://myhost/mypath/40=352214, http://myhost/mypath/46=350899, http://myhost/mypath/45=351365, http://myhost/mypath/44=350017, http://myhost/mypath/43=351334, http://myhost/mypath/49=351204, http://myhost/mypath/48=351607, http://myhost/mypath/47=351076, http://myhost/mypath/77=351352, http://myhost/mypath/76=351342, http://myhost/mypath/79=351533, http://myhost/mypath/78=351432, http://myhost/mypath/73=351226, http://myhost/mypath/72=351665, http://myhost/mypath/75=351312, http://myhost/mypath/74=351751, http://myhost/mypath/70=350102, http://myhost/mypath/71=350825, http://myhost/mypath/68=351947, http://myhost/mypath/67=350693, http://myhost/mypath/66=350610, http://myhost/mypath/65=351402, http://myhost/mypath/64=350888, http://myhost/mypath/63=352435, http://myhost/mypath/62=351706, http://myhost/mypath/61=351399, http://myhost/mypath/69=350969, http://myhost/mypath/60=351259, http://myhost/mypath/14=352765, http://myhost/mypath/15=351369, http://myhost/mypath/16=351109, http://myhost/mypath/17=351297, http://myhost/mypath/18=351685, http://myhost/mypath/19=350495, http://myhost/mypath/10=351192, http://myhost/mypath/11=350774, http://myhost/mypath/12=351069, http://myhost/mypath/13=350340, http://myhost/mypath/36=351257, http://myhost/mypath/37=351204, http://myhost/mypath/38=351907, http://myhost/mypath/39=351261, http://myhost/mypath/32=351518, http://myhost/mypath/33=351219, http://myhost/mypath/34=351317, http://myhost/mypath/35=350286, http://myhost/mypath/30=350879, http://myhost/mypath/31=352395, http://myhost/mypath/29=350400, http://myhost/mypath/27=352599, http://myhost/mypath/28=350364, http://myhost/mypath/25=350810, http://myhost/mypath/26=352008, http://myhost/mypath/23=351715, http://myhost/mypath/24=350757, http://myhost/mypath/21=351487, http://myhost/mypath/22=351877, http://myhost/mypath/20=350755, http://myhost/mypath/636=351953, http://myhost/mypath/637=351813, http://myhost/mypath/638=349964, http://myhost/mypath/639=350954, http://myhost/mypath/632=351025, http://myhost/mypath/633=350816, http://myhost/mypath/634=350323, http://myhost/mypath/635=351491, http://myhost/mypath/640=351882, http://myhost/mypath/642=351752, http://myhost/mypath/641=351288, http://myhost/mypath/629=351473, http://myhost/mypath/627=352141, http://myhost/mypath/628=350440, http://myhost/mypath/625=350986, http://myhost/mypath/626=351629, http://myhost/mypath/623=350748, http://myhost/mypath/624=351123, http://myhost/mypath/621=350861, http://myhost/mypath/622=350871, http://myhost/mypath/631=352479, http://myhost/mypath/630=351205, http://myhost/mypath/654=351022, http://myhost/mypath/655=350559, http://myhost/mypath/656=351430, http://myhost/mypath/657=352298, http://myhost/mypath/658=350812, http://myhost/mypath/659=351169, http://myhost/mypath/660=350948, http://myhost/mypath/662=350970, http://myhost/mypath/661=351283, http://myhost/mypath/664=351142, http://myhost/mypath/663=351716, http://myhost/mypath/9=350848, http://myhost/mypath/8=352037, http://myhost/mypath/7=350807, http://myhost/mypath/6=351254, http://myhost/mypath/5=352530, http://myhost/mypath/645=350916, http://myhost/mypath/646=351722, http://myhost/mypath/643=350533, http://myhost/mypath/644=352268, http://myhost/mypath/649=350776, http://myhost/mypath/647=351565, http://myhost/mypath/648=351461, http://myhost/mypath/653=350508, http://myhost/mypath/652=350815, http://myhost/mypath/651=351175, http://myhost/mypath/650=352468, http://myhost/mypath/400=351694, http://myhost/mypath/93=350973, http://myhost/mypath/92=349636, http://myhost/mypath/91=351921, http://myhost/mypath/90=351790, http://myhost/mypath/797=350463, http://myhost/mypath/798=350828, http://myhost/mypath/799=351283, http://myhost/mypath/0=351843, http://myhost/mypath/2=351912, http://myhost/mypath/1=351100, http://myhost/mypath/4=351103, http://myhost/mypath/3=352043, http://myhost/mypath/96=351870, http://myhost/mypath/97=350517, http://myhost/mypath/94=351074, http://myhost/mypath/95=351877, http://myhost/mypath/98=349887, http://myhost/mypath/99=350899, http://myhost/mypath/80=350556, http://myhost/mypath/82=351887, http://myhost/mypath/81=351587, http://myhost/mypath/788=351647, http://myhost/mypath/789=351060, http://myhost/mypath/786=351225, http://myhost/mypath/787=351370, http://myhost/mypath/796=351336, http://myhost/mypath/795=351220, http://myhost/mypath/794=351520, http://myhost/mypath/793=351149, http://myhost/mypath/792=352208, http://myhost/mypath/791=350481, http://myhost/mypath/790=352197, http://myhost/mypath/83=351034, http://myhost/mypath/84=350668, http://myhost/mypath/85=350938, http://myhost/mypath/86=351137, http://myhost/mypath/87=351366, http://myhost/mypath/88=350645, http://myhost/mypath/89=350608, http://myhost/mypath/611=351155, http://myhost/mypath/610=350210, http://myhost/mypath/613=351586, http://myhost/mypath/612=351090, http://myhost/mypath/420=351294, http://myhost/mypath/615=351861, http://myhost/mypath/614=350426, http://myhost/mypath/422=351457, http://myhost/mypath/617=351383, http://myhost/mypath/421=351345, http://myhost/mypath/616=351208, http://myhost/mypath/619=351616, http://myhost/mypath/618=350776, http://myhost/mypath/412=352021, http://myhost/mypath/413=350419, http://myhost/mypath/414=350639, http://myhost/mypath/415=351350, http://myhost/mypath/416=351493, http://myhost/mypath/417=352091, http://myhost/mypath/418=350953, http://myhost/mypath/419=352618, http://myhost/mypath/620=351298, http://myhost/mypath/602=351552, http://myhost/mypath/601=351829, http://myhost/mypath/600=350377, http://myhost/mypath/606=351477, http://myhost/mypath/411=350698, http://myhost/mypath/410=351307, http://myhost/mypath/605=352128, http://myhost/mypath/604=352154, http://myhost/mypath/603=352698, http://myhost/mypath/609=351010, http://myhost/mypath/608=351798, http://myhost/mypath/607=350572, http://myhost/mypath/409=351580, http://myhost/mypath/403=351404, http://myhost/mypath/404=351708, http://myhost/mypath/401=350891, http://myhost/mypath/402=350376, http://myhost/mypath/407=350962, http://myhost/mypath/408=351394, http://myhost/mypath/405=351902, http://myhost/mypath/406=352064, http://myhost/mypath/424=351840, http://myhost/mypath/948=350641, http://myhost/mypath/423=350730, http://myhost/mypath/949=351057, http://myhost/mypath/426=350826, http://myhost/mypath/425=349765, http://myhost/mypath/428=351651, http://myhost/mypath/427=350546, http://myhost/mypath/429=350857, http://myhost/mypath/940=350916, http://myhost/mypath/941=350771, http://myhost/mypath/942=352456, http://myhost/mypath/943=350606, http://myhost/mypath/944=350689, http://myhost/mypath/945=351669, http://myhost/mypath/750=351687, http://myhost/mypath/946=351879, http://myhost/mypath/751=350728, http://myhost/mypath/752=351295, http://myhost/mypath/947=351125, http://myhost/mypath/743=351855, http://myhost/mypath/742=350576, http://myhost/mypath/745=351123, http://myhost/mypath/744=350856, http://myhost/mypath/747=350228, http://myhost/mypath/746=351173, http://myhost/mypath/950=350861, http://myhost/mypath/749=352088, http://myhost/mypath/748=350887, http://myhost/mypath/430=351235, http://myhost/mypath/431=351356, http://myhost/mypath/432=351104, http://myhost/mypath/433=350261, http://myhost/mypath/437=350645, http://myhost/mypath/436=350694, http://myhost/mypath/959=351923, http://myhost/mypath/435=350915, http://myhost/mypath/434=351546, http://myhost/mypath/439=351748, http://myhost/mypath/438=350869, http://myhost/mypath/953=350873, http://myhost/mypath/954=351300, http://myhost/mypath/951=351384, http://myhost/mypath/952=351177, http://myhost/mypath/762=350608, http://myhost/mypath/957=350653, http://myhost/mypath/958=351883, http://myhost/mypath/763=351717, http://myhost/mypath/955=351769, http://myhost/mypath/760=351253, http://myhost/mypath/761=351226, http://myhost/mypath/956=350050, http://myhost/mypath/756=351860, http://myhost/mypath/755=351341, http://myhost/mypath/754=350775, http://myhost/mypath/753=351794, http://myhost/mypath/961=351592, http://myhost/mypath/960=351196, http://myhost/mypath/759=351690, http://myhost/mypath/758=351386, http://myhost/mypath/757=351893, http://myhost/mypath/440=351567, http://myhost/mypath/443=351166, http://myhost/mypath/444=351912, http://myhost/mypath/441=351779, http://myhost/mypath/442=350425, http://myhost/mypath/449=352012, http://myhost/mypath/446=351942, http://myhost/mypath/445=350872, http://myhost/mypath/448=351505, http://myhost/mypath/447=351807, http://myhost/mypath/966=350940, http://myhost/mypath/771=350858, http://myhost/mypath/772=351979, http://myhost/mypath/967=351593, http://myhost/mypath/773=352424, http://myhost/mypath/968=350562, http://myhost/mypath/774=350996, http://myhost/mypath/969=351850, http://myhost/mypath/962=351284, http://myhost/mypath/963=351687, http://myhost/mypath/964=352527, http://myhost/mypath/770=351670, http://myhost/mypath/965=350910, http://myhost/mypath/970=350232, http://myhost/mypath/769=351398, http://myhost/mypath/768=351338, http://myhost/mypath/972=350053, http://myhost/mypath/971=351803, http://myhost/mypath/765=350868, http://myhost/mypath/764=351814, http://myhost/mypath/767=352310, http://myhost/mypath/766=351792, http://myhost/mypath/452=351120, http://myhost/mypath/453=350332, http://myhost/mypath/454=350166, http://myhost/mypath/455=350268, http://myhost/mypath/450=352502, http://myhost/mypath/451=350127, http://myhost/mypath/459=350790, http://myhost/mypath/458=350892, http://myhost/mypath/457=350154, http://myhost/mypath/456=351425, http://myhost/mypath/784=350974, http://myhost/mypath/979=351016, http://myhost/mypath/785=351206, http://myhost/mypath/977=350433, http://myhost/mypath/782=351160, http://myhost/mypath/978=351586, http://myhost/mypath/783=351477, http://myhost/mypath/780=350975, http://myhost/mypath/975=351504, http://myhost/mypath/976=350606, http://myhost/mypath/781=351078, http://myhost/mypath/973=351818, http://myhost/mypath/974=350515, http://myhost/mypath/983=350789, http://myhost/mypath/982=350979, http://myhost/mypath/981=351390, http://myhost/mypath/980=350511, http://myhost/mypath/779=350768, http://myhost/mypath/778=351243, http://myhost/mypath/777=351112, http://myhost/mypath/776=351417, http://myhost/mypath/775=350773, http://myhost/mypath/465=351237, http://myhost/mypath/466=351543, http://myhost/mypath/463=351082, http://myhost/mypath/464=351586, http://myhost/mypath/461=349753, http://myhost/mypath/462=350660, http://myhost/mypath/460=351053, http://myhost/mypath/985=351040, http://myhost/mypath/984=351670, http://myhost/mypath/987=351721, http://myhost/mypath/986=351712, http://myhost/mypath/989=351166, http://myhost/mypath/988=351808, http://myhost/mypath/467=351272, http://myhost/mypath/468=351736, http://myhost/mypath/469=351098, http://myhost/mypath/670=351420, http://myhost/mypath/671=351111, http://myhost/mypath/672=350889, http://myhost/mypath/673=350870, http://myhost/mypath/674=351088, http://myhost/mypath/675=350782, http://myhost/mypath/666=350305, http://myhost/mypath/706=350879, http://myhost/mypath/471=351005, http://myhost/mypath/665=351087, http://myhost/mypath/470=351804, http://myhost/mypath/707=351485, http://myhost/mypath/708=351667, http://myhost/mypath/668=350913, http://myhost/mypath/473=351214, http://myhost/mypath/667=350224, http://myhost/mypath/472=351601, http://myhost/mypath/709=350470, http://myhost/mypath/280=351527, http://myhost/mypath/475=350952, http://myhost/mypath/474=351650, http://myhost/mypath/669=350357, http://myhost/mypath/477=351723, http://myhost/mypath/282=349860, http://myhost/mypath/281=351925, http://myhost/mypath/476=351615, http://myhost/mypath/284=351260, http://myhost/mypath/283=350625, http://myhost/mypath/700=350338, http://myhost/mypath/286=351331, http://myhost/mypath/285=352019, http://myhost/mypath/990=351530, http://myhost/mypath/701=351591, http://myhost/mypath/702=350531, http://myhost/mypath/288=351610, http://myhost/mypath/991=350264, http://myhost/mypath/287=351384, http://myhost/mypath/703=350781, http://myhost/mypath/992=351615, http://myhost/mypath/993=352076, http://myhost/mypath/704=351344, http://myhost/mypath/289=351375, http://myhost/mypath/705=351875, http://myhost/mypath/994=350810, http://myhost/mypath/998=350281, http://myhost/mypath/997=351211, http://myhost/mypath/996=350761, http://myhost/mypath/269=352195, http://myhost/mypath/995=352459, http://myhost/mypath/999=351674, http://myhost/mypath/681=350894, http://myhost/mypath/682=350688, http://myhost/mypath/478=351655, http://myhost/mypath/479=351557, http://myhost/mypath/680=351464, http://myhost/mypath/685=350163, http://myhost/mypath/686=351188, http://myhost/mypath/683=351655, http://myhost/mypath/684=351351, http://myhost/mypath/719=351547, http://myhost/mypath/679=352067, http://myhost/mypath/484=351429, http://myhost/mypath/678=351688, http://myhost/mypath/483=351057, http://myhost/mypath/717=350588, http://myhost/mypath/677=350839, http://myhost/mypath/482=351718, http://myhost/mypath/481=351897, http://myhost/mypath/718=351079, http://myhost/mypath/676=350904, http://myhost/mypath/488=350557, http://myhost/mypath/271=351291, http://myhost/mypath/487=350820, http://myhost/mypath/270=351498, http://myhost/mypath/486=350922, http://myhost/mypath/485=351418, http://myhost/mypath/711=351217, http://myhost/mypath/275=351478, http://myhost/mypath/712=351514, http://myhost/mypath/274=351897, http://myhost/mypath/273=351316, http://myhost/mypath/710=351604, http://myhost/mypath/272=352314, http://myhost/mypath/279=351141, http://myhost/mypath/480=350506, http://myhost/mypath/715=350321, http://myhost/mypath/716=350172, http://myhost/mypath/278=351211, http://myhost/mypath/277=350495, http://myhost/mypath/713=350556, http://myhost/mypath/276=351978, http://myhost/mypath/714=351766, http://myhost/mypath/730=350276, http://myhost/mypath/694=350759, http://myhost/mypath/695=351762, http://myhost/mypath/696=350087, http://myhost/mypath/697=351732, http://myhost/mypath/690=350422, http://myhost/mypath/489=350988, http://myhost/mypath/691=352171, http://myhost/mypath/692=351135, http://myhost/mypath/693=351321, http://myhost/mypath/497=350357, http://myhost/mypath/496=350633, http://myhost/mypath/499=352079, http://myhost/mypath/498=351220, http://myhost/mypath/728=351756, http://myhost/mypath/493=351113, http://myhost/mypath/688=351043, http://myhost/mypath/729=352235, http://myhost/mypath/687=350830, http://myhost/mypath/492=351336, http://myhost/mypath/495=349768, http://myhost/mypath/689=351026, http://myhost/mypath/494=352000, http://myhost/mypath/724=350765, http://myhost/mypath/725=351461, http://myhost/mypath/491=351656, http://myhost/mypath/726=350455, http://myhost/mypath/727=351643, http://myhost/mypath/490=350938, http://myhost/mypath/720=350907, http://myhost/mypath/721=351604, http://myhost/mypath/722=351406, http://myhost/mypath/723=351290, http://myhost/mypath/741=350291, http://myhost/mypath/740=351056, http://myhost/mypath/293=351608, http://myhost/mypath/292=351323, http://myhost/mypath/291=350443, http://myhost/mypath/290=351559, http://myhost/mypath/739=350899, http://myhost/mypath/699=351330, http://myhost/mypath/698=350246, http://myhost/mypath/737=350429, http://myhost/mypath/738=351072, http://myhost/mypath/299=352068, http://myhost/mypath/735=351829, http://myhost/mypath/298=350795, http://myhost/mypath/736=351428, http://myhost/mypath/733=349421, http://myhost/mypath/297=350340, http://myhost/mypath/734=352341, http://myhost/mypath/296=352096, http://myhost/mypath/295=350382, http://myhost/mypath/731=351387, http://myhost/mypath/294=351130, http://myhost/mypath/732=350585, http://myhost/mypath/241=351388, http://myhost/mypath/242=351888, http://myhost/mypath/240=350303, http://myhost/mypath/245=351142, http://myhost/mypath/246=350716, http://myhost/mypath/243=352299, http://myhost/mypath/244=351867, http://myhost/mypath/239=350971, http://myhost/mypath/238=350472, http://myhost/mypath/237=351835, http://myhost/mypath/236=351835, http://myhost/mypath/230=350409, http://myhost/mypath/231=351055, http://myhost/mypath/232=351115, http://myhost/mypath/233=350300, http://myhost/mypath/234=349762, http://myhost/mypath/235=350995, http://myhost/mypath/226=351007, http://myhost/mypath/225=350630, http://myhost/mypath/228=352672, http://myhost/mypath/227=351810, http://myhost/mypath/229=352504, http://myhost/mypath/267=351773, http://myhost/mypath/268=350517, http://myhost/mypath/265=351476, http://myhost/mypath/266=351572, http://myhost/mypath/263=351756, http://myhost/mypath/264=351027, http://myhost/mypath/261=350787, http://myhost/mypath/262=350984, http://myhost/mypath/260=350752, http://myhost/mypath/259=351206, http://myhost/mypath/258=350048, http://myhost/mypath/254=351292, http://myhost/mypath/255=350718, http://myhost/mypath/256=350862, http://myhost/mypath/257=351464, http://myhost/mypath/250=350977, http://myhost/mypath/251=351815, http://myhost/mypath/252=351740, http://myhost/mypath/253=351099, http://myhost/mypath/248=351961, http://myhost/mypath/247=351329, http://myhost/mypath/249=351850, http://myhost/mypath/202=351587, http://myhost/mypath/201=350464, http://myhost/mypath/200=351058, http://myhost/mypath/903=351424, http://myhost/mypath/902=351390, http://myhost/mypath/901=352004, http://myhost/mypath/900=351281, http://myhost/mypath/907=350745, http://myhost/mypath/906=351104, http://myhost/mypath/905=351137, http://myhost/mypath/904=350706, http://myhost/mypath/909=350734, http://myhost/mypath/908=351589, http://myhost/mypath/910=349994, http://myhost/mypath/912=351525, http://myhost/mypath/911=350897, http://myhost/mypath/914=351065, http://myhost/mypath/913=351253, http://myhost/mypath/916=351176, http://myhost/mypath/915=352386, http://myhost/mypath/918=351658, http://myhost/mypath/917=350910, http://myhost/mypath/919=351561, http://myhost/mypath/224=351241, http://myhost/mypath/223=351156, http://myhost/mypath/222=352080, http://myhost/mypath/221=350733, http://myhost/mypath/220=350751, http://myhost/mypath/925=351509, http://myhost/mypath/924=351177, http://myhost/mypath/218=351762, http://myhost/mypath/923=351495, http://myhost/mypath/922=352357, http://myhost/mypath/219=349732, http://myhost/mypath/921=351981, http://myhost/mypath/216=351251, http://myhost/mypath/920=350669, http://myhost/mypath/217=351399, http://myhost/mypath/214=351244, http://myhost/mypath/215=351220, http://myhost/mypath/929=350236, http://myhost/mypath/928=351108, http://myhost/mypath/927=351455, http://myhost/mypath/926=350566, http://myhost/mypath/211=349881, http://myhost/mypath/210=351240, http://myhost/mypath/213=350727, http://myhost/mypath/212=351889, http://myhost/mypath/207=351520, http://myhost/mypath/934=350265, http://myhost/mypath/208=351450, http://myhost/mypath/933=350827, http://myhost/mypath/209=350634, http://myhost/mypath/936=351113, http://myhost/mypath/935=351076, http://myhost/mypath/203=351147, http://myhost/mypath/930=350905, http://myhost/mypath/204=351653, http://myhost/mypath/932=351207, http://myhost/mypath/205=350956, http://myhost/mypath/931=350443, http://myhost/mypath/206=350880, http://myhost/mypath/938=351679, http://myhost/mypath/937=351179, http://myhost/mypath/939=351211, http://myhost/mypath/533=351265, http://myhost/mypath/534=351036, http://myhost/mypath/535=351771, http://myhost/mypath/536=350990, http://myhost/mypath/537=351680, http://myhost/mypath/538=351758, http://myhost/mypath/539=351553, http://myhost/mypath/541=350828, http://myhost/mypath/540=351651, http://myhost/mypath/543=350707, http://myhost/mypath/542=351742, http://myhost/mypath/524=351645, http://myhost/mypath/525=350575, http://myhost/mypath/522=350473, http://myhost/mypath/523=351462, http://myhost/mypath/528=351069, http://myhost/mypath/529=351009, http://myhost/mypath/526=351218, http://myhost/mypath/527=350552, http://myhost/mypath/532=350618, http://myhost/mypath/531=351694, http://myhost/mypath/530=352042, http://myhost/mypath/515=351825, http://myhost/mypath/516=350600, http://myhost/mypath/517=351145, http://myhost/mypath/518=351862, http://myhost/mypath/511=350694, http://myhost/mypath/512=350591, http://myhost/mypath/513=351619, http://myhost/mypath/514=351843, http://myhost/mypath/519=351678, http://myhost/mypath/521=351907, http://myhost/mypath/520=351299, http://myhost/mypath/506=351273, http://myhost/mypath/507=352156, http://myhost/mypath/504=350701, http://myhost/mypath/505=351042, http://myhost/mypath/502=351814, http://myhost/mypath/503=351515, http://myhost/mypath/500=351616, http://myhost/mypath/501=352204, http://myhost/mypath/508=351344, http://myhost/mypath/509=350839, http://myhost/mypath/510=351570, http://myhost/mypath/301=351513, http://myhost/mypath/300=351473, http://myhost/mypath/892=351577, http://myhost/mypath/893=350919, http://myhost/mypath/894=352128, http://myhost/mypath/895=351039, http://myhost/mypath/890=350618, http://myhost/mypath/891=350068, http://myhost/mypath/329=351043, http://myhost/mypath/328=351445, http://myhost/mypath/325=351327, http://myhost/mypath/324=351198, http://myhost/mypath/327=350678, http://myhost/mypath/326=351148, http://myhost/mypath/331=351248, http://myhost/mypath/332=351718, http://myhost/mypath/333=350948, http://myhost/mypath/334=351897, http://myhost/mypath/330=351148, http://myhost/mypath/889=351127, http://myhost/mypath/886=350734, http://myhost/mypath/885=351400, http://myhost/mypath/888=351191, http://myhost/mypath/887=351397, http://myhost/mypath/339=351182, http://myhost/mypath/338=350548, http://myhost/mypath/337=351388, http://myhost/mypath/336=351886, http://myhost/mypath/335=350023, http://myhost/mypath/344=351575, http://myhost/mypath/345=351872, http://myhost/mypath/342=352325, http://myhost/mypath/343=350475, http://myhost/mypath/340=350322, http://myhost/mypath/341=349803, http://myhost/mypath/899=350587, http://myhost/mypath/898=351201, http://myhost/mypath/897=352522, http://myhost/mypath/896=351016, http://myhost/mypath/870=351420, http://myhost/mypath/871=350856, http://myhost/mypath/872=351053, http://myhost/mypath/590=350751, http://myhost/mypath/873=350595, http://myhost/mypath/303=350652, http://myhost/mypath/592=350148, http://myhost/mypath/302=351416, http://myhost/mypath/591=351857, http://myhost/mypath/305=350714, http://myhost/mypath/594=352603, http://myhost/mypath/593=350962, http://myhost/mypath/304=352160, http://myhost/mypath/307=352197, http://myhost/mypath/596=351452, http://myhost/mypath/595=351820, http://myhost/mypath/306=352385, http://myhost/mypath/309=350577, http://myhost/mypath/598=351830, http://myhost/mypath/597=350762, http://myhost/mypath/308=351153, http://myhost/mypath/588=350025, http://myhost/mypath/589=351353, http://myhost/mypath/310=351329, http://myhost/mypath/311=351327, http://myhost/mypath/312=350215, http://myhost/mypath/864=351572, http://myhost/mypath/863=350307, http://myhost/mypath/866=352418, http://myhost/mypath/865=351204, http://myhost/mypath/868=351612, http://myhost/mypath/867=351952, http://myhost/mypath/869=352529, http://myhost/mypath/880=351778, http://myhost/mypath/883=351262, http://myhost/mypath/884=351797, http://myhost/mypath/881=351872, http://myhost/mypath/882=351032, http://myhost/mypath/316=350621, http://myhost/mypath/315=351107, http://myhost/mypath/314=352242, http://myhost/mypath/313=351327, http://myhost/mypath/319=349857, http://myhost/mypath/318=351964, http://myhost/mypath/317=350918, http://myhost/mypath/190=351826, http://myhost/mypath/599=350820, http://myhost/mypath/322=351623, http://myhost/mypath/193=351215, http://myhost/mypath/323=351836, http://myhost/mypath/194=351631, http://myhost/mypath/320=351788, http://myhost/mypath/191=351290, http://myhost/mypath/321=350470, http://myhost/mypath/192=352264, http://myhost/mypath/197=351381, http://myhost/mypath/877=351695, http://myhost/mypath/876=351593, http://myhost/mypath/198=351604, http://myhost/mypath/195=351041, http://myhost/mypath/875=351978, http://myhost/mypath/196=351751, http://myhost/mypath/874=351073, http://myhost/mypath/199=351069, http://myhost/mypath/879=351244, http://myhost/mypath/878=351455, http://myhost/mypath/573=351440, http://myhost/mypath/574=350566, http://myhost/mypath/575=351688, http://myhost/mypath/576=350552, http://myhost/mypath/368=350912, http://myhost/mypath/570=351426, http://myhost/mypath/369=351288, http://myhost/mypath/571=352071, http://myhost/mypath/572=351144, http://myhost/mypath/851=351426, http://myhost/mypath/850=351957, http://myhost/mypath/845=351223, http://myhost/mypath/189=350499, http://myhost/mypath/188=350514, http://myhost/mypath/846=351956, http://myhost/mypath/370=350740, http://myhost/mypath/847=351468, http://myhost/mypath/848=351421, http://myhost/mypath/841=351949, http://myhost/mypath/185=351118, http://myhost/mypath/184=349909, http://myhost/mypath/842=350210, http://myhost/mypath/843=352540, http://myhost/mypath/187=350668, http://myhost/mypath/844=350772, http://myhost/mypath/186=351626, http://myhost/mypath/181=351746, http://myhost/mypath/376=350840, http://myhost/mypath/180=350908, http://myhost/mypath/375=350378, http://myhost/mypath/378=351623, http://myhost/mypath/183=351672, http://myhost/mypath/377=352000, http://myhost/mypath/182=350551, http://myhost/mypath/849=350744, http://myhost/mypath/372=350864, http://myhost/mypath/567=351357, http://myhost/mypath/566=350800, http://myhost/mypath/371=350106, http://myhost/mypath/569=351273, http://myhost/mypath/374=351700, http://myhost/mypath/373=351283, http://myhost/mypath/568=351836, http://myhost/mypath/586=349881, http://myhost/mypath/587=350746, http://myhost/mypath/584=351386, http://myhost/mypath/585=350905, http://myhost/mypath/582=351171, http://myhost/mypath/583=351348, http://myhost/mypath/580=351803, http://myhost/mypath/379=352097, http://myhost/mypath/581=351299, http://myhost/mypath/862=350559, http://myhost/mypath/861=351558, http://myhost/mypath/860=350817, http://myhost/mypath/381=351886, http://myhost/mypath/858=349872, http://myhost/mypath/179=350538, http://myhost/mypath/859=351659, http://myhost/mypath/380=351408, http://myhost/mypath/178=350999, http://myhost/mypath/856=350713, http://myhost/mypath/857=350555, http://myhost/mypath/177=350753, http://myhost/mypath/176=351778, http://myhost/mypath/854=351194, http://myhost/mypath/855=351489, http://myhost/mypath/175=351768, http://myhost/mypath/852=350940, http://myhost/mypath/174=349935, http://myhost/mypath/173=350990, http://myhost/mypath/853=351457, http://myhost/mypath/172=351755, http://myhost/mypath/389=351517, http://myhost/mypath/171=351500, http://myhost/mypath/388=351301, http://myhost/mypath/170=349848, http://myhost/mypath/387=350627, http://myhost/mypath/386=350511, http://myhost/mypath/385=351136, http://myhost/mypath/579=352489, http://myhost/mypath/384=350638, http://myhost/mypath/578=352044, http://myhost/mypath/383=350912, http://myhost/mypath/577=351206, http://myhost/mypath/382=350936, http://myhost/mypath/346=350666, http://myhost/mypath/347=351438, http://myhost/mypath/348=350136, http://myhost/mypath/550=352188, http://myhost/mypath/349=350467, http://myhost/mypath/551=350652, http://myhost/mypath/552=351160, http://myhost/mypath/553=350539, http://myhost/mypath/554=351101, http://myhost/mypath/159=351835, http://myhost/mypath/163=351383, http://myhost/mypath/162=351024, http://myhost/mypath/820=349935, http://myhost/mypath/165=351797, http://myhost/mypath/821=351255, http://myhost/mypath/164=351521, http://myhost/mypath/822=351645, http://myhost/mypath/167=350891, http://myhost/mypath/823=350912, http://myhost/mypath/824=351154, http://myhost/mypath/166=352115, http://myhost/mypath/169=351432, http://myhost/mypath/825=351564, http://myhost/mypath/826=350635, http://myhost/mypath/168=351210, http://myhost/mypath/545=350821, http://myhost/mypath/350=352181, http://myhost/mypath/827=350538, http://myhost/mypath/544=351366, http://myhost/mypath/828=350516, http://myhost/mypath/352=351545, http://myhost/mypath/829=350598, http://myhost/mypath/547=350599, http://myhost/mypath/546=351676, http://myhost/mypath/351=350706, http://myhost/mypath/549=350802, http://myhost/mypath/354=351407, http://myhost/mypath/548=350510, http://myhost/mypath/353=351441, http://myhost/mypath/356=351023, http://myhost/mypath/161=351414, http://myhost/mypath/355=351297, http://myhost/mypath/160=352614, http://myhost/mypath/560=351861, http://myhost/mypath/359=352191, http://myhost/mypath/561=350881, http://myhost/mypath/357=351090, http://myhost/mypath/358=351741, http://myhost/mypath/564=350797, http://myhost/mypath/565=351702, http://myhost/mypath/562=351069, http://myhost/mypath/563=351214, http://myhost/mypath/148=350027, http://myhost/mypath/149=352150, http://myhost/mypath/840=351338, http://myhost/mypath/832=350188, http://myhost/mypath/154=350685, http://myhost/mypath/153=351735, http://myhost/mypath/833=351120, http://myhost/mypath/830=350834, http://myhost/mypath/152=350156, http://myhost/mypath/151=351618, http://myhost/mypath/831=351727, http://myhost/mypath/836=352007, http://myhost/mypath/158=352218, http://myhost/mypath/837=351032, http://myhost/mypath/157=351056, http://myhost/mypath/156=351710, http://myhost/mypath/834=352233, http://myhost/mypath/155=351724, http://myhost/mypath/835=351280, http://myhost/mypath/363=352061, http://myhost/mypath/558=351797, http://myhost/mypath/557=351108, http://myhost/mypath/362=351266, http://myhost/mypath/838=351629, http://myhost/mypath/361=351876, http://myhost/mypath/556=351471, http://myhost/mypath/839=351222, http://myhost/mypath/360=351043, http://myhost/mypath/555=352058, http://myhost/mypath/150=351920, http://myhost/mypath/367=351860, http://myhost/mypath/366=351022, http://myhost/mypath/365=350060, http://myhost/mypath/559=350906, http://myhost/mypath/364=351864, http://myhost/mypath/809=351158, http://myhost/mypath/808=351368, http://myhost/mypath/807=350728, http://myhost/mypath/806=351762, http://myhost/mypath/805=352379, http://myhost/mypath/804=351337, http://myhost/mypath/146=351141, http://myhost/mypath/803=351206, http://myhost/mypath/147=350326, http://myhost/mypath/144=352445, http://myhost/mypath/802=351608, http://myhost/mypath/801=350678, http://myhost/mypath/145=351061, http://myhost/mypath/142=351301, http://myhost/mypath/800=351002, http://myhost/mypath/143=351822, http://myhost/mypath/140=351944, http://myhost/mypath/141=351379, http://myhost/mypath/139=350812, http://myhost/mypath/138=351431, http://myhost/mypath/137=351636, http://myhost/mypath/817=349853, http://myhost/mypath/816=352411, http://myhost/mypath/819=350498, http://myhost/mypath/818=350455, http://myhost/mypath/813=350936, http://myhost/mypath/133=351231, http://myhost/mypath/134=350161, http://myhost/mypath/812=350744, http://myhost/mypath/135=351917, http://myhost/mypath/815=351924, http://myhost/mypath/136=351728, http://myhost/mypath/814=351182, http://myhost/mypath/130=351749, http://myhost/mypath/131=350537, http://myhost/mypath/811=351206, http://myhost/mypath/810=350836, http://myhost/mypath/132=351613, http://myhost/mypath/127=351134, http://myhost/mypath/126=351458, http://myhost/mypath/129=351002, http://myhost/mypath/128=350649, http://myhost/mypath/395=351562, http://myhost/mypath/396=350843, http://myhost/mypath/393=350198, http://myhost/mypath/394=351291, http://myhost/mypath/399=350773, http://myhost/mypath/397=350471, http://myhost/mypath/398=350814, http://myhost/mypath/120=352010, http://myhost/mypath/121=351365, http://myhost/mypath/391=351635, http://myhost/mypath/124=350529, http://myhost/mypath/125=351051, http://myhost/mypath/392=350975, http://myhost/mypath/122=351809, http://myhost/mypath/390=351746, http://myhost/mypath/123=352013, http://myhost/mypath/118=350416, http://myhost/mypath/117=351399, http://myhost/mypath/116=351420, http://myhost/mypath/115=351656, http://myhost/mypath/119=350969, http://myhost/mypath/110=351742, http://myhost/mypath/111=351708, http://myhost/mypath/112=350533, http://myhost/mypath/113=351629, http://myhost/mypath/114=351246, http://myhost/mypath/105=350556, http://myhost/mypath/104=352392, http://myhost/mypath/107=351062, http://myhost/mypath/106=350646, http://myhost/mypath/109=350893, http://myhost/mypath/108=350744, http://myhost/mypath/103=351424, http://myhost/mypath/102=352291, http://myhost/mypath/101=352007, http://myhost/mypath/100=351746}

		
		
	}
	
	static void readFileThread() throws Exception{
		
	}
	
	static void mergeFile() throws Exception{
		System.out.println(1024*1024);
		System.out.println("start");
		String filePath = "D:\\aa\\";
		RandomAccessFile file = new RandomAccessFile("D:\\aa\\__apache-maven-3.2.1-bin.zip", "rw");
		long pos = 0;
		for(int i=0;i<2;i++){
			RandomAccessFile fileCopy = new RandomAccessFile(filePath + "fileCopy"+i, "rw");
			int len = (int)fileCopy.length();
			byte[] b = new byte[len];
			fileCopy.readFully(b);
			
			file.write(b);
			
			pos += len;
			System.out.println(fileCopy.length());
			file.seek(pos);
		}
		System.out.println("done!");
	}
	
	static void splitFile() throws Exception{
		String filePath = "D:\\aa\\";
//		RandomAccessFile file = new RandomAccessFile("D:\\aa\\dc_zyk_czrk_rkxx.dmp", "rw");
//		RandomAccessFile file = new RandomAccessFile("D:\\aa\\apache-maven-3.2.1-bin.zip", "rw");
//		RandomAccessFile file = new RandomAccessFile("D:\\aa\\(z)install_flash_player_11_plugin.exe", "rw");
		RandomAccessFile file = new RandomAccessFile("D:\\aa\\log.log", "rw");
		
		file.seek(0);
//		RandomAccessFile fileCopy = new RandomAccessFile(filePath + "fileCopy.log", "rw");
		
		long len = file.length();
		
		int size = 1 * 1 * 1024;//复制M
//		int size = 1 * 1024 * 1024;//复制M
		
		long count = len/size;
		int mod = (int)len%size;
		System.out.println(count + "--" + mod);
		
		count++;
		
		int i = 0;
		for(i=0;i<count;i++){
//			System.out.println(i);
			byte[] b;
			if(i==count-1){
				b = new byte[mod];
			}else{
				b = new byte[size];
			}

			System.out.println(b.length);
			
			file.readFully(b);
			file.seek(size);
			
			RandomAccessFile fileCopy = new RandomAccessFile(filePath + "fileCopy"+i, "rw");
			fileCopy.write(b);
		}
		
		System.out.println("done!");
		
		
	
	}
	
	static void copy() throws Exception{
		//以下演示文件复制操作 
		String filePath = "D:\\aa\\";
		RandomAccessFile file = new RandomAccessFile("D:\\aa\\dc_zyk_czrk_rkxx.dmp", "rw");
		
		System.out.println(file.length());
		System.out.println(file.readLine());
		
		
		System.out.println("——————文件复制（从file到fileCopy）——————");
		file.seek(0);
		RandomAccessFile fileCopy = new RandomAccessFile(filePath + "fileCopy.log", "rw");
		
//		int len = (int) file.length();// 取得文件长度（字节数）
		
		int size = 128 * 1024 * 1024;//复制512M
		byte[] b = new byte[size];
		file.readFully(b);
		
		System.out.println("--"+b[0]);
		
		fileCopy.write(b);
		System.out.println("复制完成！");
	}
	
	
	static void logFile() throws Exception{
		String pathname = "D:\\aa\\log.log";
		File log = new File(pathname);
		
		FileOutputStream write = new FileOutputStream(log);
		
//		BufferedWriter br = new BufferedWriter(out);
		
		byte[] line = "nhttp://myhost/mypath/\n".getBytes();
		String s = "http://myhost/mypath/";
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(int j=0;j<1000;j++){
			map.put(j, s+j+"\n");
		}
		
		
		long i = 0;
		long n = Long.MAX_VALUE;
		while(i<n){
//			System.out.print(i+":"+new String(line));
//			write.write(line);
			Random r = new Random();
			int t = r.nextInt(1000);
			String url = map.get(t);
//			System.out.print(url);
			write.write(url.getBytes());
//			write.write((s+t+"\n").getBytes());
//			write.write((s+i+"\n").getBytes());
			i++;
		}
		
		
	}
	
	

}
