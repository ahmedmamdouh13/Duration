package com.ahmedmamdouh13.customcalendarview

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ahmedmamdouh13 on 28/02/18.
 */

class DateUtil  {


    private var formatterOutForCalendar: SimpleDateFormat
    private val formatterOut: SimpleDateFormat
    private val simpleDateFormat: SimpleDateFormat
    private val visibleDate: Calendar

    //        Intent i = new Intent(context, MyService.class);
    //        i.putExtra("foo", "bar");
    //        context.startService(i);
    // date1=simpleDateFormat.parse("2018-10-09");
    //   date2=simpleDateFormat.parse("2000-02-02");
    val date: Date
        get() {

            var date1: Date

            val calendar = Calendar.getInstance()
            date1 = calendar.time


            try {
                date1 = simpleDateFormat.parse(simpleDateFormat.format(date1))

            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return date1
        }

    init {
        simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        visibleDate = GregorianCalendar()
        formatterOut = SimpleDateFormat("yyyy MMM dd")
        formatterOutForCalendar = SimpleDateFormat("yyyy MMM EEE")
    }

    fun isThatDateToday(date: String): Boolean {

        var dateEp: Date? = null
        try {
            if (date !== "")
                dateEp = simpleDateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return if (dateEp != null) {
            dateEp == this.date
        } else
            false
    }

    fun isTheseDatesEqual(airdate: String, year: Int, month: Int, dayOfMonth: Int): Boolean {


        //Calendar calendar=new GregorianCalendar(year,month,dayOfMonth);
        val date = GregorianCalendar(year, month, dayOfMonth)



        var chosenDate: Date? = null
        var dateEp: Date? = null
        try {
            chosenDate = simpleDateFormat.parse(simpleDateFormat.format(date.time))
            dateEp = simpleDateFormat.parse(airdate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return if (chosenDate != null && dateEp != null) {
            chosenDate == dateEp
        } else {
            false
        }
    }

    fun getVisibleDate(year: Int, month: Int, dayOfMonth: Int): String {

        visibleDate.set(year, month, dayOfMonth)


        return if (formatterOut.format(visibleDate.time) == formatterOut.format(Calendar.getInstance().time)) {

            "TODAY"

        } else
            formatterOut.format(visibleDate.time)
    }

    fun getVisibleDateDay(year: Int, month: Int, dayOfMonth: Int): String {

        visibleDate.set(year, month, dayOfMonth)


//        return if (formatterOutForCalendar.format(visibleDate.time) == formatterOutForCalendar.format(Calendar.getInstance().time)) {
//
//            "TODAY"
//
//        } else
        return formatterOutForCalendar.format(visibleDate.time)
    }

    fun getConvertedDate(showTime: String, showTimeZone: String, year: Int, month: Int, dayOfMonth: Int): String {
        var year1 = year
        var month1 = month
        var dayOfMonth1 = dayOfMonth
        try {
            val instance = Calendar.getInstance()
            val time1date = instance.time
            val split = showTime.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()


            if (year1 == -1) {
                year1 = instance.get(Calendar.YEAR)
                month1 = instance.get(Calendar.MONTH)
                dayOfMonth1 = instance.get(Calendar.DAY_OF_MONTH)
            }

            val calendar = GregorianCalendar(
                    year1, month1, dayOfMonth1, Integer.valueOf(split[0]), Integer.valueOf(split[1]))

            val userTimeZone = TimeZone.getDefault().id


            val simpleDateFormat = SimpleDateFormat("EEE dd-MMM , hh:mm a")


            val zone = TimeZone.getTimeZone(showTimeZone)

            calendar.timeZone = zone


            // DateFormat format = simpleDateFormat;
            //  format.setTimeZone(zone);


            // println(format.format(Date()))

            simpleDateFormat.timeZone = TimeZone.getTimeZone(userTimeZone)
            println(userTimeZone)
            // println(format.format(Date()))

            //  Date date = new Date();

            return simpleDateFormat.format(calendar.time)
        } catch (e: Exception) {
            return "not available"
        }

    }


    fun getFormattedAirtime(airtime: String): String {
        try {
            val simpleDateFormat = SimpleDateFormat("hh:mm a")
            val split = airtime.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val instance = Calendar.getInstance()


            val year = instance.get(Calendar.YEAR)
            val month = instance.get(Calendar.MONTH)
            val dayOfMonth = instance.get(Calendar.DAY_OF_MONTH)


            val calendar = GregorianCalendar(
                    year, month, dayOfMonth, Integer.valueOf(split[0]), Integer.valueOf(split[1]))


            return simpleDateFormat.format(calendar.time)
        } catch (e: Exception) {
            return "not available"
        }

    }

    fun getIntDateFromString(airDate: String): IntArray {
        val split = airDate.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return intArrayOf(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]))
    }

    fun afterToday(date: String): Boolean {

        var dateEp: Date? = null
        try {
            if (date !== "")
                dateEp = simpleDateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return dateEp?.after(this.date) ?: false


    }

    fun beforeToday(date: String): Boolean {
        var dateEp: Date? = null
        try {
            if (date !== "")
                dateEp = simpleDateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return dateEp?.before(this.date) ?: false

    }

    fun afterDate(airdate: String, year: Int, month: Int, dayOfMonth: Int): Boolean {
        val date = GregorianCalendar(year, month, dayOfMonth)



        var chosenDate: Date? = null
        var dateEp: Date? = null
        try {
            chosenDate = simpleDateFormat.parse(simpleDateFormat.format(date.time))
            dateEp = simpleDateFormat.parse(airdate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return if (chosenDate != null && dateEp != null) {
            dateEp.after(chosenDate)
        } else {
            false
        }
    }

    fun beforeDate(airdate: String, year: Int, month: Int, dayOfMonth: Int): Boolean {
        val date = GregorianCalendar(year, month, dayOfMonth)



        var chosenDate: Date? = null
        var dateEp: Date? = null
        try {
            chosenDate = simpleDateFormat.parse(simpleDateFormat.format(date.time))
            dateEp = simpleDateFormat.parse(airdate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return if (chosenDate != null && dateEp != null) {
            dateEp.before(chosenDate)
        } else {
            false
        }
    }


}
