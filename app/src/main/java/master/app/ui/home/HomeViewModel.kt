package master.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pregled različitih tipova DR programa (cenovno zasnovani, podsticajni, itd.)."
    }
    val text: LiveData<String> = _text

    // You can add more LiveData properties for different sections if needed
}
