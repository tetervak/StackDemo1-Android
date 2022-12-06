package ca.tetervak.stackdemo.data.database

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

    @Provides
    fun provideStackItemDao(database: StackDatabase): StackItemDao {
        return database.stackItemDao()
    }

    @Singleton
    @Provides
    fun provideStackDatabase(@ApplicationContext context: Context): StackDatabase {
        return Room.databaseBuilder(
            context,
            StackDatabase::class.java,
            "stack_database"
        ).build()
    }

}