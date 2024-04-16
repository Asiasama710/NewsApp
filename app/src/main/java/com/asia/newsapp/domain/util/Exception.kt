package com.asia.newsapp.domain.util

open class NewsAppException : Exception()
class InternalServerException : NewsAppException()
class BadRequestException : NewsAppException()
class UnAuthorizedException : NewsAppException()
class TooManyRequestsException : NewsAppException()
class NetworkException : NewsAppException()
class NotFoundException : NewsAppException()