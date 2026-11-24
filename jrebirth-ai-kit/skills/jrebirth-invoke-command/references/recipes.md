# Command invocation recipes


### WaveData

```java
callCommand(ApplyShellNavigationCommand.class,
        WBuilder.waveData(ApplyShellNavigationCommand.INTENT, intent));
```

### WaveBean

```java
callCommand(ShowCompetitionMainPageCommand.class,
        ShowCompetitionMainPageWaveBean.forPage(page));
```

### FORCE_SYNC / multi-bean wave

```java
sendWave(WBuilder.callCommand(ShowSlidingModelCommand.class)
        .waveBean(displayBean)
        .waveBean(slideBean)
        .addDatas(JRebirthWaves.FORCE_SYNC));
```

Or when only WaveData is needed:

```java
callCommand(EnsureSecondaryStageCommand.class, JRebirthWaves.FORCE_SYNC);
```

