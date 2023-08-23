package com.brainjug.shoppinglisttestinge.di

import android.content.Context
import androidx.room.Room
import com.brainjug.shoppinglisttestinge.data.local.ShoppingDao
import com.brainjug.shoppinglisttestinge.data.local.ShoppingItemDatabase
import com.brainjug.shoppinglisttestinge.data.remote.PixabayAPI
import com.brainjug.shoppinglisttestinge.other.Constants
import com.brainjug.shoppinglisttestinge.other.Constants.BASE_URL
import com.brainjug.shoppinglisttestinge.repositories.DefaultShoppingRepository
import com.brainjug.shoppinglisttestinge.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, Constants.DATABASE_NAME)
        .build()

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }
}