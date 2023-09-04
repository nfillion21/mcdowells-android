package pgm.poolp.mcdowells.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import pgm.poolp.core.data.BigMickDataSource
import pgm.poolp.mcdowells.framework.data.BigMickDataSourceImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class BigMickDataSourceModule {
    @ActivityScoped
    @Binds
    abstract fun bindBigMickDataSourceImpl(impl: BigMickDataSourceImpl): BigMickDataSource
}