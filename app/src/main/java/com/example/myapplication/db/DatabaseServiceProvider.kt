package com.example.myapplication.db

class DatabaseServiceProvider {
    companion object {
        val db : DatabaseService = InMemoryDatabaseService().connect()
    }
}