//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import androidx.viewpager2.adapter.FragmentStateAdapter
//import com.nisa.finlyrenew.PengajuanFragment
//import com.nisa.finlyrenew.PinjamanFragment
//
//class PengajuanPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
//
//    override fun getItemCount(): Int = 2
//
//    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> PengajuanFragment()
//            1 -> PinjamanFragment()
//            else -> throw IllegalArgumentException("Invalid position")
//        }
//    }
//}