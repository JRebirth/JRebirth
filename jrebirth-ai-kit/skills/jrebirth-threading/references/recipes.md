# Threading recipes


### Already on JAT (Model return handler)

```java
public void doLoadItemsDone(final List<Item> items, final Wave wave) {
    rows().setAll(items != null ? items : List.of());
    // already JAT — no runIntoJAT
}
```

### Refresh wave (JAT UI refresh from elsewhere)

```java
// emit
sendWave(FeatureWaves.DO_REFRESH_LIST);

// Model
listen(FeatureWaves.DO_REFRESH_LIST);
public void doRefreshList(final Wave wave) {
    refreshList();
}
```

### UI Command (named JAT action)

```java
public final class RefreshListCommand extends DefaultUICommand {
    @Override
    protected void perform(final Wave wave) {
        getModel(ItemsModel.class).refreshList();
    }
}
// callCommand(RefreshListCommand.class);
```

### JIT / pool Command

```java
// JIT messaging / short sync work
public final class ApplyFilterCommand extends DefaultCommand { … }

// JTP heavy local work
public final class ParseImportCommand extends DefaultPoolCommand { … }
```

