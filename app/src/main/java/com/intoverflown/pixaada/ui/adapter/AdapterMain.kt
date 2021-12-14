import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.intoverflown.pixaada.data.Hit
import com.intoverflown.pixaada.databinding.ItemGridViewBinding


class AdapterMain(rvData: List<Hit>, mContext: Context) :
    RecyclerView.Adapter<AdapterMain.RVHolder>() {
    var rvData: List<Hit> = rvData
    var binding: ItemGridViewBinding? = null
    var mContext: Context? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RVHolder {
        binding = ItemGridViewBinding.inflate(LayoutInflater.from(viewGroup.context))
        val view: View = binding!!.root
        return RVHolder(view)
    }

    override fun onBindViewHolder(holder: RVHolder, i: Int) {
//        holder.cusName.setText(rvData[i].getCustomerName())
        //        String dt = rvData.get(i).getStatus();
//        holder.trxDate.setText("");

//        val batchId: String = rvData[i].getBatchID()
//        val customerName: String = rvData[i].getCustomerName()
//        val balance: String = rvData[i].getBalance()
//        val status: String = rvData[i].getStatus()
//        holder.lHolder.setOnClickListener { view: View? ->
//            val j = Intent(mContext, TrxCsCrDetailActivity::class.java)
//            j.putExtra("batchID", batchId)
//            j.putExtra("cusName", customerName)
//            j.putExtra("balance", balance)
//            j.putExtra("pStatus", status)
//            mContext.startActivity(j)
//        }
    }

    override fun getItemCount(): Int {
        return rvData.size
    }

    class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var previewImg: ImageView

        init {
            val itemGridViewBinding: ItemGridViewBinding = ItemGridViewBinding.bind(itemView)
            previewImg = itemGridViewBinding.previewImage
        }
    }

}