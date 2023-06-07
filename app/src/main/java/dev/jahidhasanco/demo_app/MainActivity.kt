package dev.jahidhasanco.demo_app


import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import dev.jahidhasanco.seatbookview.SeatBookView
import dev.jahidhasanco.seatbookview.SeatClickListener
import dev.jahidhasanco.seatbookview.SeatLongClickListener


class MainActivity : AppCompatActivity() {

    private lateinit var seatBookView: SeatBookView
    private var seats = (
            "/U___S" +
                    "/_____" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RR_RR" +
                    "/RRRRR"

            )


    private var title = listOf(
        "/", "1", "", "", "", "E5",
        "/", "", "", "", "", "",
        "/", "2", "3", "", "4", "5",
        "/", "6", "7", "", "8", "9",
        "/", "10", "11", "", "12", "13",
        "/", "14", "15", "", "16", "17",
        "/", "18", "19", "", "20", "21",
        "/", "22", "23", "", "24", "25",
        "/", "26", "27", "", "28", "29",
        "/", "30", "31", "", "32", "33",
        "/", "34", "35", "", "36", "37",
        "/", "38", "39", "", "40", "41",
        "/", "42", "43", "", "44", "45",
        "/", "46", "47", "", "48", "49",
        "/", "50", "51", "52", "53", "54"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seatBookView = findViewById(R.id.layoutSeat)

        val positionsAvailable = listOf(4, 10, 25)
         val bookedSeats = listOf(1, 34, 21)
        seats = replaceLettersAtPositions(seats, positionsAvailable, 'A')
        seats = replaceLettersAtPositions(seats, bookedSeats, 'U')


        seatBookView.setSeatsLayoutString(seats)
            .isCustomTitle(true)
            .setCustomTitle(title)
            .setSeatLayoutPadding(2)
            .setSeatSizeBySeatsColumnAndLayoutWidth(5, -1)
        //ParentLayoutWeight -1 if Your seatBookView layout_width = match_parent / wrap_content


        seatBookView.show()


        seatBookView.getSeatView(2).apply {
            seatBookView.markAsTransparentSeat(this as TextView)
            this.setBackgroundResource(R.drawable.ic_steering)
            this.setPadding(5)
        }

        seatBookView.setSeatClickListener(object : SeatClickListener {

            override fun onAvailableSeatClick(selectedIdList: List<Int>, view: View) {


            }

            override fun onBookedSeatClick(view: View) {


            }

            override fun onReservedSeatClick(view: View) {

            }


        })

        seatBookView.setSeatLongClickListener(object : SeatLongClickListener {

            override fun onAvailableSeatLongClick(view: View) {
                Toast.makeText(this@MainActivity, "Long Pressed", Toast.LENGTH_SHORT).show()
            }

            override fun onBookedSeatLongClick(view: View) {

            }

            override fun onReservedSeatLongClick(view: View) {

            }

        })

    }

    fun replaceLettersAtPositions(seats: String, positions: List<Int>, replacement: Char): String {
        val charArray = seats.toCharArray()
        for (position in positions) {
            var count = 0
            for (i in charArray.indices) {
                if (charArray[i] != '_' && charArray[i] != '/' && charArray[i] != '+'  && charArray[i] != 'S') {
                    count++
                    if (count == position) {
                        charArray[i] = replacement
                        break
                    }
                }
            }
        }
        return String(charArray)
    }

}