import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerCompose(onDateSelected: (String) -> Unit) {
    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return true
            }
        }
    )

    val selectedDate = datePickerState.selectedDateMillis

    selectedDate?.let { date ->
        val formattedDate = convertMillisToDate(date)
        onDateSelected(formattedDate)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DatePicker(state = datePickerState)
        Spacer(modifier = Modifier.height(32.dp))
        // Display selected date
        Text(
            text = "Selected Date: ${selectedDate?.let { convertMillisToDate(it) } ?: "Not selected"}",
            color = Color.Red
        )
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(Date(millis))
}
