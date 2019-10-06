package com.ahmedmamdouh13.domain.status

class MyResult<T>(var data: T) {

    var message: String = ""
    var status = Status.SUCCESS

}