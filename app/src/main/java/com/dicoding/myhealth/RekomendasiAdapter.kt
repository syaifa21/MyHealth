
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myhealth.R
import com.dicoding.myhealth.api.RekomendasiKaloriItem



class RekomendasiAdapter(private val rekomendasiList: List<RekomendasiKaloriItem>) :
    RecyclerView.Adapter<RekomendasiAdapter.RekomendasiViewHolder>() {

    class RekomendasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val food: TextView = itemView.findViewById(R.id.namefood)
        val calories: TextView = itemView.findViewById(R.id.kalori)
        val fat: TextView = itemView.findViewById(R.id.lemak)
        val protein: TextView = itemView.findViewById(R.id.protein)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RekomendasiViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return RekomendasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: RekomendasiViewHolder, position: Int) {
        val rekomendasiItem = rekomendasiList[position]
        holder.food.text = rekomendasiItem.food
        holder.calories.text = rekomendasiItem.calories.toString()
        holder.fat.text = rekomendasiItem.fat.toString()
        holder.protein.text = rekomendasiItem.protein.toString()

//        // Add additional checks to ensure that the data is not null
//        if (rekomendasiItem.food.isNullOrBlank()) {
//            holder.food.text = "No Data"
//        }
//        if (rekomendasiItem.calories == 0) {
//            holder.calories.text = "No Data"
//        }
//        if (rekomendasiItem.fat == 0) {
//            holder.fat.text = "No Data"
//        }
//        if (rekomendasiItem.protein == 0) {
//            holder.protein.text = "No Data"
//        }
    }

    override fun getItemCount(): Int {
        return rekomendasiList.size
    }
}
