package com.omasyo.comicsnac.data.di

import com.omasyo.comicsnac.data.character.CharacterRepository
import com.omasyo.comicsnac.data.character.DefaultCharacterRepository
import com.omasyo.comicsnac.data.concept.ConceptRepository
import com.omasyo.comicsnac.data.concept.DefaultConceptRepository
import com.omasyo.comicsnac.data.episode.DefaultEpisodeRepository
import com.omasyo.comicsnac.data.episode.EpisodeRepository
import com.omasyo.comicsnac.data.issue.DefaultIssueRepository
import com.omasyo.comicsnac.data.issue.IssueRepository
import com.omasyo.comicsnac.data.location.DefaultLocationRepository
import com.omasyo.comicsnac.data.location.LocationRepository
import com.omasyo.comicsnac.data.movie.DefaultMovieRepository
import com.omasyo.comicsnac.data.movie.MovieRepository
import com.omasyo.comicsnac.data.`object`.DefaultObjectRepository
import com.omasyo.comicsnac.data.`object`.ObjectRepository
import com.omasyo.comicsnac.data.origin.DefaultOriginRepository
import com.omasyo.comicsnac.data.origin.OriginRepository
import com.omasyo.comicsnac.data.person.DefaultPersonRepository
import com.omasyo.comicsnac.data.person.PersonRepository
import com.omasyo.comicsnac.data.power.DefaultPowerRepository
import com.omasyo.comicsnac.data.power.PowerRepository
import com.omasyo.comicsnac.data.publisher.DefaultPublisherRepository
import com.omasyo.comicsnac.data.publisher.PublisherRepository
import com.omasyo.comicsnac.data.search.DefaultSearchRepository
import com.omasyo.comicsnac.data.search.SearchRepository
import com.omasyo.comicsnac.data.series.DefaultSeriesRepository
import com.omasyo.comicsnac.data.series.SeriesRepository
import com.omasyo.comicsnac.data.settings.AuthRepository
import com.omasyo.comicsnac.data.settings.DefaultAuthRepository
import com.omasyo.comicsnac.data.settings.DefaultSettingsRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.data.storyarc.DefaultStoryArcRepository
import com.omasyo.comicsnac.data.storyarc.StoryArcRepository
import com.omasyo.comicsnac.data.team.DefaultTeamRepository
import com.omasyo.comicsnac.data.team.TeamRepository
import com.omasyo.comicsnac.data.volume.DefaultVolumeRepository
import com.omasyo.comicsnac.data.volume.VolumeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(characterRepository: DefaultCharacterRepository): CharacterRepository

    @Binds
    fun bindConceptRepository(conceptRepository: DefaultConceptRepository): ConceptRepository

    @Binds
    fun bindEpisodeRepository(episodeRepository: DefaultEpisodeRepository): EpisodeRepository

    @Binds
    fun bindIssueRepository(issueRepository: DefaultIssueRepository): IssueRepository

    @Binds
    fun bindLocationRepository(locationRepository: DefaultLocationRepository): LocationRepository

    @Binds
    fun bindMovieRepository(movieRepository: DefaultMovieRepository): MovieRepository

    @Binds
    fun bindObjectRepository(objectRepository: DefaultObjectRepository): ObjectRepository

    @Binds
    fun bindOriginRepository(originRepository: DefaultOriginRepository): OriginRepository

    @Binds
    fun bindPersonRepository(personRepository: DefaultPersonRepository): PersonRepository

    @Binds
    fun bindPowerRepository(powerRepository: DefaultPowerRepository): PowerRepository

    @Binds
    fun bindPublisherRepository(publisherRepository: DefaultPublisherRepository): PublisherRepository

    @Binds
    fun bindSearchRepository(searchRepository: DefaultSearchRepository): SearchRepository

    @Binds
    fun bindSeriesRepository(seriesRepository: DefaultSeriesRepository): SeriesRepository

    @Binds
    fun bindStoryArcRepository(storyArcRepository: DefaultStoryArcRepository): StoryArcRepository

    @Binds
    fun bindTeamRepository(teamRepository: DefaultTeamRepository): TeamRepository

    @Binds
    fun bindVolumeRepository(volumeRepository: DefaultVolumeRepository): VolumeRepository

    @Binds
    fun bindSettingsRepository(settingsRepository: DefaultSettingsRepository): SettingsRepository

    @Binds
    fun bindAuthRepository(authRepository: DefaultAuthRepository): AuthRepository
}
