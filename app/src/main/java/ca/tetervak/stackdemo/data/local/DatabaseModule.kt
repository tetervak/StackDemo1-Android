package ca.tetervak.stackdemo.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideItemDatabase(
        @ApplicationContext applicationContext: Context
    ): ItemDatabase = Room.databaseBuilder(
        applicationContext, ItemDatabase::class.java, "item_database"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideItemDao(
        database: ItemDatabase
    ): ItemDao = database.itemDao
}