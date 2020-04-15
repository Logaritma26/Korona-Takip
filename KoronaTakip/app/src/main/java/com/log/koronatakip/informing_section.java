package com.log.koronatakip;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class informing_section extends Fragment {


    //TextView textView_main;
    ViewPager viewPager_inform;
    ViewPagerAdapter_inform adapter;

    private ArrayList<String> main_text_title = new ArrayList<>();

    private ArrayList<String> page1_title = new ArrayList<>();
    private ArrayList<String> page1_content = new ArrayList<>();

    private ArrayList<String> page2_title = new ArrayList<>();
    private ArrayList<String> page2_content = new ArrayList<>();

    private ArrayList<String> page3_title = new ArrayList<>();
    private ArrayList<String> page3_content = new ArrayList<>();

    private ArrayList<String> page4_title = new ArrayList<>();
    private ArrayList<String> page4_content = new ArrayList<>();

    private ArrayList<String> page5_title = new ArrayList<>();
    private ArrayList<String> page5_content = new ArrayList<>();



    Context context;
    Activity activity_inform;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.context = context;
            activity_inform = (Activity) context;
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.informing_section, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager_inform = view.findViewById(R.id.viewPager_informing_main);








        INIT_CONTENT();


        adapter = new ViewPagerAdapter_inform(context, main_text_title,
                page1_title, page1_content,
                page2_title, page2_content,
                page3_title, page3_content,
                page4_title, page4_content,
                page5_title, page5_content);

        viewPager_inform.setAdapter(adapter);
        viewPager_inform.setClipToPadding(false);
        viewPager_inform.setPageMargin(200);













    }

    private void INIT_CONTENT() {

        main_text_title.add("Belirtiler");


        main_text_title.add("Nasıl Yayılır");


        main_text_title.add("Yeni koronavirüse karşı temel koruyucu önlemler");


        main_text_title.add("Maske Çeşitleri");


        main_text_title.add("Maskeler ne zaman ve nasıl kullanılır?");


        page1_title.add("");
        page1_content.add("COVID-19 virüsü farklı insanları farklı şekillerde etkiler. COVID-19 solunum yolu hastalığıdır ve enfekte kişilerin çoğu hafif ve orta şiddette semptomlar geliştirir ve özel tedavi gerektirmeden iyileşir. Altta yatan tıbbi rahatsızlıkları olan ve 60 yaşın üzerindeki kişilerde şiddetli hastalık ve ölüm gelişme riski daha yüksektir.\n\n" +
                "   Yaygın semptomlar şunları içerir: \n\n" +
                "- Ateş \n" +
                "- Yorgunluk \n" +
                "- Kuru öksürük. \n\n" +
                "   Diğer belirtiler şunları içerir: \n\n" +
                "- Nefes darlığı \n" +
                "- Ağrı ve sancı \n" +
                "- Boğaz ağrısı \n" +
                "-Ve çok az insanda ishal, bulantı veya burun akıntısı rapor edilmiştir. \n\n"+
                "Hafif semptomları olan kişiler, kendilerini izole etmeli, test ve sevk konusunda da tavsiye için tıbbi sağlayıcılarına veya bir COVID-19 bilgi hattına başvurmalıdır.\n" +
                "Ateşi, öksürüğü veya nefes darlığı olan kişiler doktorlarını aramalı ve tıbbi yardım almalıdır.\n\n\n\n\n");


        page2_title.add("");
        page2_title.add("COVID-19'a neden olan virüs hava yoluyla bulaşabilir mi?");
        page2_title.add("CoVID-19 hiçbir semptomu olmayan bir kişiden bulaşabilir mi?");
        page2_title.add("Hastalığı olan birinin dışkısından COVID-19'a yakalanabilir miyim?");

        page2_content.add("İnsanlar COVID-19'u virüsü olan diğerlerinden yakalayabilir. Hastalık, kişiden kişiye, COVID-19'lu bir kişi öksürdüğünde veya nefes verdiğinde yayılan küçük damlacıklardan veya ağızdan yayılabilir. Bu damlacıklar kişinin etrafındaki nesnelere ve yüzeylere düşer. Diğer insanlar daha sonra bu nesnelere veya yüzeylere dokunarak, sonra gözlerine, burnuna veya ağzına dokunarak COVID-19'u yakalarlar. İnsanlar ayrıca, COVID-19'lu damlacıkları öksüren veya nefes veren bir kişiden damlacıklar halinde nefes alırlarsa COVID-19'u yakalayabilirler. Bu yüzden hasta olan bir kişiden 1 metreden (3 feet) daha uzak durmak önemlidir.\n" +
                "DSÖ, COVID-19'un yayılma yolları konusunda devam eden araştırmaları değerlendirmektedir ve güncellenmiş bulguları paylaşmaya devam edecektir.");
        page2_content.add("Bugüne kadar yapılan çalışmalar, COVID-19'a neden olan virüsün esas olarak hava yerine solunum damlacıkları ile temas yoluyla bulaştığını göstermektedir. “COVID-19 nasıl yayılır?” Hakkındaki önceki cevaba bakınız.");
        page2_content.add("Hastalığın ana yolu öksüren biri tarafından atılan solunum damlacıklarıdır. Hiç semptomu olmayan bir kişiden COVID-19'u yakalama riski çok düşüktür. Bununla birlikte, COVID-19 olan birçok insan sadece hafif semptomlar yaşar. Bu özellikle hastalığın erken evrelerinde geçerlidir. Bu nedenle, örneğin hafif bir öksürüğü olan ve hasta hissetmeyen bir kişiden COVID-19'u yakalamak mümkündür. DSÖ, COVID-19'un bulaşma süresi üzerine devam eden araştırmaları değerlendirmektedir ve güncellenmiş bulguları paylaşmaya devam edecektir.");
        page2_content.add("Enfekte bir kişinin dışkısından COVID-19'u yakalama riski düşük gibi görünmektedir. İlk araştırmalar bazı durumlarda virüsün dışkıda mevcut olabileceğini düşünse de, bu yoldan yayılma salgının ana özelliği değildir. DSÖ, COVID-19'un yayılma yolları konusunda devam eden araştırmaları değerlendirmektedir ve yeni bulguları paylaşmaya devam edecektir. Ancak bu bir risk olduğundan, banyoyu kullandıktan sonra ve yemekten önce ellerin düzenli olarak temizlenmesi başka bir nedendir.\n\n\n\n\n\n");

        page3_title.add("");
        page3_title.add("Ellerinizi sık sık yıkayın");
        page3_title.add("Sosyal mesafeyi koruyun");
        page3_title.add("Göz, burun ve ağza dokunmaktan kaçının");
        page3_title.add("Solunum hijyenini uygulayın");
        page3_title.add("Ateş, öksürük ve nefes almada zorluk çekiyorsanız, erken tıbbi yardım alın");
        page3_title.add("Haberdar olun ve sağlık uzmanınız tarafından verilen tavsiyelere uyun");

        page3_content.add("DSÖ web sitesinde ve ulusal ve yerel halk sağlığı otoritenizde bulunan COVID-19 salgınıyla ilgili en son bilgilerden haberdar olun. Enfekte olan çoğu insan hafif bir hastalık yaşar ve iyileşir, ancak diğerleri için daha şiddetli olabilir. Aşağıdakileri yaparak sağlığınıza dikkat edin ve başkalarını koruyun:");
        page3_content.add("Ellerinizi düzenli olarak ve alkol bazlı bir el ovarak temizleyin veya sabun ve su ile yıkayın.\n" +
                "Neden? Ellerinizi sabun ve su ile yıkamak veya alkol bazlı el ovmak kullanarak ellerinizde olabilecek virüsleri öldürür.");
        page3_content.add("Kendinizle öksüren veya hapşıran herkes arasında en az 1 metre (3 feet) mesafe bırakın.\n" +
                "Neden? Birisi öksürdüğünde veya hapşırdığında, burun veya ağzından virüs içerebilecek küçük sıvı damlacıkları püskürtür. Çok yakınsanız, öksüren kişinin hastalığı varsa, COVID-19 virüsü de dahil olmak üzere damlacıklarda nefes alabilirsiniz.");
        page3_content.add("Neden? Eller birçok yüzeye dokunur ve virüsleri alabilir. Kirlendiğinde eller virüsü gözlerinize, burnunuza veya ağzınıza aktarabilir. Oradan, virüs vücudunuza girebilir ve sizi hasta edebilir.\n");
        page3_content.add("Sizin ve çevrenizdeki insanların iyi bir solunum hijyeni uyguladığından emin olun. Bu, öksürdüğünüzde veya hapşırdığınızda ağzınızı ve burnunuzu bükülmüş dirsek veya doku ile kaplamak anlamına gelir. Ardından kullanılmış dokuyu hemen atın.\n" +
                "Neden? Damlacıklar virüs yayıldı. İyi bir solunum hijyeni uygulayarak çevrenizdeki kişileri soğuk algınlığı, grip ve COVID-19 gibi virüslerden korursunuz.\n");
        page3_content.add("Kendinizi iyi hissetmiyorsanız evde kalın. Ateş, öksürük ve nefes almada zorluk çekiyorsanız, tıbbi yardım alın ve önceden arayın. Yerel sağlık kurumunuzun talimatlarını izleyin.\n" +
                "Neden? Ulusal ve yerel yetkililer bölgenizdeki durum hakkında en güncel bilgilere sahip olacaktır. Önceden aramak, sağlık uzmanınızın sizi hızlı bir şekilde doğru sağlık kuruluşuna yönlendirmesini sağlayacaktır. Bu ayrıca sizi koruyacak ve virüslerin ve diğer enfeksiyonların yayılmasını önlemeye yardımcı olacaktır.");
        page3_content.add("COVID-19 hakkındaki son gelişmelerden haberdar olun. Kendinizi ve başkalarını COVID-19'dan nasıl koruyacağınız konusunda sağlık uzmanınız, ulusal ve yerel halk sağlığı otoritesi veya işvereniniz tarafından verilen tavsiyelere uyun.\n" +
                "Neden? Ulusal ve yerel yetkililer, COVID-19'un bölgenize yayılıp yayılmadığına dair en güncel bilgilere sahip olacaklardır. En iyi, bölgenizdeki insanların kendilerini korumak için ne yapmaları gerektiği konusunda tavsiyelerde bulunmak için yerleştirilir.\n\n\n\n\n\n");



        page4_title.add("Ev yapımı yüz maskeleri");
        page4_title.add("Cerrahi maskeler");
        page4_title.add("N95 solunum maskeleri");


        page4_content.add("Ev yapımı yüz maskeleri sadece küçük bir koruma derecesi sunar, ancak SARS-CoV-2'nin asemptomatik kişilerden yayılmasını önlemeye yardımcı olabilirler. CDC, kamusal ortamlarda kullanılmasının yanı sıra sosyal mesafe ve uygun hijyen uygulamalarını önerir.");
        page4_content.add("SARS-CoV-2'nin asemptomatik kişilerden yayılmasını önlemeye yardımcı olabilirler. CDC, kamusal ortamlarda kullanılmasının yanı sıra sosyal mesafe ve uygun hijyen uygulamalarını önerir.");
        page4_content.add("Hastalık Kontrol ve Önleme Merkezleri (CDC), genel halkın kendilerini koronavirüs (COVID-19) dahil olmak üzere solunum hastalıklarından korumak için N95 solunum cihazı takmasını önermez. Bunlar, mevcut CDC kılavuzluğunda önerildiği gibi, sağlık çalışanları ve diğer tıbbi müdahale ekipleri için ayrılmaya devam etmesi gereken kritik malzemelerdir.\n\n\n\n\n\n");



        page5_title.add("Maske ne zaman kullanılır?");
        page5_title.add("Koronavirüse karşı ne zaman ve nasıl tıbbi maske takılmalıdır?");


        page5_content.add("Sağlıklıysanız, sadece 2019-nCoV enfeksiyonu şüphesi olan bir kişiye bakıyorsanız maske takmanız gerekir.\n" +
                "Öksürüyorsanız veya hapşırıyorsanız bir maske takın.\n" +
                "Maskeler yalnızca alkol bazlı el ovma veya sabun ve su ile sık sık el temizliği ile birlikte kullanıldığında etkilidir.\n" +
                "Bir maske takarsanız, nasıl kullanılacağını bilmeli ve uygun şekilde imha etmelisiniz.");
        page5_content.add("Bir maskeyi takmadan önce, elleri alkol bazlı el ovma veya sabun ve su ile temizleyin.\n" +
                "Ağız ve burnu maske ile örtün ve yüzünüzle maske arasında boşluk olmadığından emin olun.\n" +
                "Maskeyi kullanırken dokunmaktan kaçının; yaparsanız, ellerinizi alkol bazlı el ovma veya sabun ve su ile temizleyin.\n" +
                "Maskeyi nemli olduğu anda yenisiyle değiştirin ve tek kullanımlık maskeleri tekrar kullanmayın.\n" +
                "Maskeyi çıkarmak için: arkadan çıkarın (maskenin önüne dokunmayın); derhal kapalı bir çöp kutusuna atın; elleri alkol bazlı el ovma veya sabun ve su ile temizleyin.\n\n\n\n\n\n");
    }
}
