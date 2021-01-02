package com.example.anticovid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anticovid.Adapter.FAQAdapter;
import com.example.anticovid.Model.FAQ;
import com.example.anticovid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentFaq extends Fragment {
    private View view;
    private List<FAQ> listfaq;
    private RecyclerView recyclerView;
    private FAQAdapter faqAdapter ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_faq,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerfaq);
        init();
        faqAdapter= new FAQAdapter(listfaq);
        recyclerView.setAdapter(faqAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void init() {
        listfaq  = new ArrayList<>();
        listfaq.add(new FAQ(R.drawable.imgstudent,"Dành cho học sinh,sinh viên","- Rửa tay với nước sạch và xà phòng theo quy trình rửa tay của Bộ Y tế tại các thời điểm: Trước khi vào lớp học, trước và sau khi ăn, sau mỗi giờ ra chơi và nghỉ giữa giờ, sau khi đi vệ sinh, khi thấy tay bẩn.\n " +
                "1. Che mũi, miệng khi ho hoặc hắt hơi, tốt nhất bằng giấy lau sạch, khăn vải hoặc khăn tay, hoặc ống tay áo để làm giảm phát tán dịch tiết đường hô hấp. Vứt bỏ khăn, giấy che mũi, miệng vào thùng rác và rửa sạch tay; Bỏ rác đúng nơi quy định.\n" +
                "2. Không đưa tay lên mắt, mũi, miệng để tránh lây nhiễm bệnh.\n" +
                "3. Không dùng chung các đồ dùng cá nhân như cốc, chai nước, khăn tay, gối, chăn...\n" +
                "4. Nghiêm cấm học sinh khạc nhổ bừa bãi.\n" +
                "5. Hàng ngày, trước khi vào giờ học, giáo viên điểm danh và hỏi học sinh xem có cảm thấy sốt, ho, khó thở, mệt mỏi không (đối với trẻ mầm non thì hỏi cha mẹ khi giáo viên nhận trẻ). Nếu có, giáo viên đưa ngay học sinh đến phòng y tế để kiểm tra, theo dõi và xử trí kịp thời.\n"));
        listfaq.add(new FAQ(R.drawable.imgbighouse,"Dành cho chung cư","1. Thường xuyên rửa tay với xà phòng và nước sạch, đặc biệt ngay sau khi về nhà.\n" +
                "2. Che kín mũi, miệng khi ho hoặc hắt hơi bằng khuỷu tay áo, khăn vải hoặc khăn tay hoặc khăn giấy. Giặt sạch khăn hoặc bỏ ngay khăn giấy vào thùng rác sau khi sử dụng và rửa sạch tay. Bỏ rác đúng nơi quy định.\n" +
                "3. Tránh đưa tay lên mắt, mũi, miệng; Không khạc nhổ bừa bãi.\n" +
                "4. Súc miệng, súc họng bằng nước muối hoặc nước súc miệng thường xuyên.\n" +
                "5. Giữ ấm cơ thể, tập thể dục, ăn chín, uống chín và đảm bảo chế độ ăn đầy đủ dinh dưỡng.\n" +
                "6. Thay và giặt sạch quần áo đi làm, đi chơi hoặc đến chỗ đông người ngay sau khi về nhà.\n" +
                "7. Hạn chế nói chuyện, hạn chế tiếp xúc trực tiếp với các bề mặt khi sử dụng cầu thang máy, cầu thang bộ.\n" +
                "8. Hạn chế đến chỗ đông người. Đeo khẩu trang khi đến chỗ đông người.\n" +
                "9. Hạn chế tiếp xúc với người có biểu hiện ho, sốt, khó thở. Giữ khoảng cách trên 2 mét và đeo khẩu trang nếu phải tiếp xúc.\n" +
                "10. Vệ sinh nhà cửa sạch sẽ hàng ngày bằng các dung dịch tẩy rửa thông thường. Đặc biệt là tay nắm cửa, tay vịn cầu thang, tay vịn lan can.\n" +
                "11. Thường xuyên mở cửa ra vào và cửa sổ; hạn chế sử dụng điều hòa.\n" +
                "12. Thu gom rác hàng ngày và đổ đúng nơi quy định.\n" +
                ""));
        listfaq.add(new FAQ(R.drawable.imgfamily,"Dành cho gia đình","1. Thường xuyên rửa tay với xà phòng và nước sạch tại các thời điểm: Ngay sau khi về nhà. Sau khi ho, hắt hơi. Sau khi cầm, nắm, tiếp xúc với các vật dụng có nguy cơ như cửa, tay nắm cửa, công tắc điện, bồn cầu… Sau khi đi vệ sinh. Sau khi vệ sinh cho trẻ, người ốm. Trước khi ăn. Trước và sau khi chế biến thực phẩm. Sau khi tiếp xúc với động vật, vật nuôi. Khi bàn tay bẩn. Thời gian rửa tay ít nhất 30 giây và thực hiện đúng quy trình rửa tay 6 bước.\n" +
                "2. Khi ho hoặc hắt hơi: Che kín mũi, miệng bằng khuỷu tay áo, khăn vải hoặc khăn tay hoặc khăn giấy. Giặt sạch khăn hoặc bỏ ngay khăn giấy vào thùng rác sau khi sử dụng. Rửa sạch tay với xà phòng và nước sạch.\n" +
                "3. Hạn chế tiếp xúc với người có biểu hiện ho, sốt, khó thở. Giữ khoảng cách tối thiểu và luôn đeo khẩu trang nếu phải tiếp xúc.\n" +
                "4. Không tụ tập đông người tại nhà; hạn chế đến nơi đông người, giữ khoảng cách tối thiểu với người xung quanh; không khạc nhổ, vứt rác, khẩu trang bừa bãi ra môi trường.\n" +
                "5. Vệ sinh nhà cửa đảm bảo thông thoáng không khí trong nhà.\n "));
        listfaq.add(new FAQ(R.drawable.imgschool,"Dành cho trường học","1. Khẩu trang: Khuyến khích toàn bộ học sinh, giáo viên và nhân viên sử dụng khẩu trang đúng cách và lâu dài để phòng tránh lây truyền SARS-CoV-2 qua các giọt bắn từ đường hô hấp. Trường hợp ngoại lệ không cần sử dụng khẩu trang gồm trẻ em dưới 2 tuổi và người có vấn đề nhận thức, cảm giác hoặc hành vi hay người hỗ trợ những người này.\n" +
                "2. Cách ly giao tiếp xã hội tối đa: Duy trì khoảng cách tối thiểu 6 feet giữa mọi người với nhau. Tìm hiểu thêm về việc thúc đẩy các hành vi giảm bớt tình trạng lây lan COVID-19.\n" +
                "3. Vệ sinh tay và quy ước vệ sinh cơ quan hô hấp: Dạy và củng cố thao tác rửa tay bằng xà phòng và nước trong ít nhất 20 giây và tăng cường giám sát để đảm bảo học sinh và nhân viên tuân thủ. Khuyến khích học sinh và nhân viên che miệng, mũi khi ho và hắt hơi bằng khăn giấy và rửa tay ngay sau khi xì mũi, ho hay hắt hơi. Người khuyết tật có thể cần trợ giúp trong việc giữ vệ sinh tay. Tìm hiểu thêm về việc thúc đẩy thói quen vệ sinh tay và quy ước vệ sinh cơ quan hô hấp.\n" +
                "4. Làm sạch và khử trùng: Làm sạch và khử trùng các bề mặt tiếp xúc thường xuyên (VD: trang thiết bị sân chơi, tay nắm cửa, tay cầm bồn rửa, bồn cầu, vòi uống nước) trong trường và trên xe buýt của trường ít nhất mỗi ngày một lần hoặc càng nhiều càng tốt sau mỗi lần sử dụng.\n" +
                "5. Truy dấu người tiếp xúc: Phối hợp với sở y tế địa phương để truy dấu người tiếp xúc với học sinh, giáo viên và nhân viên mắc bệnh một cách có hệ thống.\n"));
        listfaq.add(new FAQ(R.drawable.imgpersion,"Dành cho người mắc bệnh","1. Ở nhà ngoại trừ cần được chăm sóc y tế.\n" +
                "• Không đi làm, đi học hoặc đến những khu vực công cộng.\n" +
                "• Ở nhà và tránh xa những người khác cho đến khi quý vị đáp ứng tất cả các điều kiện cần\n" +
                "thiết để kết thúc thời gian cách ly một cách an toàn. (Xem ô bên dưới để biết thêm thông\n" +
                "tin về việc kết thúc cách ly.)\n" +
                "• Nếu quý vị phải rời khỏi nhà trong khi quý vị bị bệnh, vui lòng không sử dụng phương tiện\n" +
                "giao thông công cộng. Sử dụng phương tiện cá nhân nếu có thể. Nếu quý vị không thể tự\n" +
                "lái xe, hãy giữ khoảng cách càng xa càng tốt giữa quý vị và người lái xe, mở cửa sổ khi lái,\n" +
                "và đeo khẩu trang hoặc khăn che mặt.\n" +
                "• Bố trí cho thực phẩm và các nhu yếu phẩm khác được giao đến nhà của quý vị và để lại ở\n" +
                "cửa nhà quý vị nếu quý vị không có ai có thể giúp quý vị với những nhu thiết yếu này. Gọi\n" +
                "2-1-1 để biết thêm về những dịch vụ hiện có. Nếu quý vị cần gặp ai đó ở cửa, hãy đeo khăn\n" +
                "che mặt.\n" +
                "2. Cách ly bản thân mình với người khác trong gia đình.\n" +
                "• Chọn một phòng cụ thể trong gia đình quý vị mà sẽ được sử dụng để tách quý vị khỏi\n" +
                "những người khác trong khi quý vị phục hồi - Ở trong phòng này cách xa những người khác\n" +
                "trong gia đình quý vị càng lâu càng tốt. Tránh xa những người có nguy cơ mắc trọng bệnh\n" +
                "cao (hơn người khác) khi bị nhiễm là điều cực kỳ quan trọng.\n" +
                "• Sử dụng phòng tắm riêng, nếu có thể. Nếu sử dụng phòng tắm riêng là không thể, thì vệ\n" +
                "sinh và khử trùng phòng tắm sau mỗi lần sử dụng như được nêu dưới đây.\n" +
                "Thực hành giữ khoảng cách giao tiếp khi ở gần người khác trong gia đình quý vị - Cố gắng\n" +
                "duy trì khoảng cách ít nhất 6 feet (2m) với những người khác.\n" +
                "• Mở cửa sổ hoặc sử dụng quạt hoặc điều hòa ở không gian chung trong gia đình để đảm\n" +
                "bảo luồng khí lưu thông tốt.\n" +
                "• Không tiếp khách ở nhà.\n" +
                "• Không bế ẵm thú cưng hoặc động vật khi quý vị ốm.\n" +
                "• Không chuẩn bị hoặc phục vụ đồ ăn cho người khác.\n" +
                "• Không chăm sóc trẻ hoặc người khác trong gia đình quý vị nếu có thể.\n" +
                "• Nếu việc tách ly bản thân với những người khác trong gia đình là không thể, hoặc nếu quý\n" +
                "vị sống với những cá nhân có nguy cơ mắc trọng bệnh cao (hơn người khác) nếu bị nhiễm\n" +
                "COVID-19 (chẳng hạn như người cao tuổi, những người bị bệnh mãn tính, hoặc hệ miễn\n" +
                "dịch suy yếu), thì vui lòng trao đổi với y bác sĩ chăm sóc sức khỏe của quý vị về bố trí nơi\n" +
                "sống khác. Họ có thể giới thiệu quý vị đến chỗ ở tạm thời trong khi quý vị phục hồi.\n"));
        listfaq.add(new FAQ(R.drawable.imgworkspace,"Dành cho nơi làm việc","1. Tuân thủ các quy định về phòng chống dịch của chính quyền địa phương nơi đến công tác.\n" +
                " \n" +
                "2. Người lao động cần tuân thủ việc rửa tay thường xuyên bằng xà phòng để giữ gìn vệ sinh cá nhân\n" +
                "\n" +
                "3. Rửa tay thường xuyên, giữ gìn vệ sinh cá nhân khi ho, hắt hơi. Tránh xa ít nhất 02 mét đối với những người đang ho hoặc hắt hơi.\n" +
                "\n" +
                "4. Trong khi đi công tác, nếu có một trong các biểu hiện sốt, ho, đau họng, khó thở cần đeo khẩu trang, tránh tiếp xúc với những người xung quanh, thông báo với người quản lý, gọi điện cho đường dây nóng của Sở Y tế hoặc Bộ Y tế (số điện thoại 1900 3228 hoặc 1900 9095) và đến cơ sở y tế gần nhất để được tư vấn và điều trị kịp thời.\n" +
                "\n" +
                "5. Hướng dẫn của Bộ Y tế cũng nêu rõ, sau khi đi công tác về từ khu vực có nguy cơ lây nhiễm COVID-19: Người lao động tự theo dõi các triệu chứng trong 14 ngày và đo nhiệt độ 02 lần một ngày.\n" +
                "\n" +
                "6. Nếu có một trong các biểu hiện sốt, ho, đau họng, khó thở người lao động phải cần đeo khẩu trang, tránh tiếp xúc với những người xung quanh, gọi điện cho đường dây nóng của Sở Y tế hoặc Bộ Y tế (số điện thoại 1900 3228 hoặc 1900 9095) và đến cơ sở y tế gần nhất để được hướng dẫn và cách ly chặt chẽ tại cơ sở y tế.\n" +
                "\n" +
                "7. Thông báo cho người quản lý hoặc/và người làm công tác y tế tại nơi làm việc để thông báo cho những người tiếp xúc gần tại nơi làm việc tự theo dõi sức khỏe và đến cơ sở y tế khi cần thiết.\n"));
    }

}
