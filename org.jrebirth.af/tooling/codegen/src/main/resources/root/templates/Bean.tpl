{!-- Create Template --}
{#Creator}

return new {$bean.name}();

{#}


{!-- Simple Getter Template --}
{#Getter}

if (this.{$bean.property_name} != null) {
    return this.{$bean.property_name}.get();
}
return this.{$bean.name};

{#}


{#Setter}

if ({$bean.property_name} != null) {
    {$bean.property_name}.set({$bean.name});
}
this.{$bean.name} = {$bean.name};
return {$bean.fluent_type}this;

{#}


{#PropertyGetter}

if ({$bean.property_name} == null) {
    {$bean.property_name} = new {$bean.concrete_property_type}();
    {$bean.property_name}.set({$bean.name});
}
return this.{$bean.property_name};

{#}


{#Getter_List}

if ({$bean.property_name} != null) {
    return {$bean.property_name}.stream().collect(Collectors.toList());
}
if (this.{$bean.name} == null) {
    this.{$bean.name} = new ArrayList<>();
}
return this.{$bean.name};

{#}


{#Setter_List}

if ({$bean.property_name} != null) {
    {$bean.property_name}.setAll({$bean.name});
}
this.{$bean.name} = {$bean.name};
return {$bean.fluent_type}this;

{#}



{#PropertyGetter_List}

if ({$bean.property_name} == null) {
    {$bean.property_name} = FXCollections.observableList(this.{$bean.name}());
}
return this.{$bean.property_name};

{#}

{#Getter_Map}

if ({$bean.property_name} != null) {
    return {$bean.property_name}.stream().collect(Collectors.toList());
}
if (this.{$bean.name} == null) {
    this.{$bean.name} = new ArrayList<>();
}
return this.{$bean.name};

{#}


{#Setter_Map}

if ({$bean.property_name} != null) {
    {$bean.property_name}.setAll({$bean.name});
}
this.{$bean.name} = {$bean.name};
return {$bean.fluent_type}this;

{#}


{#PropertyGetter_Map}

if ({$bean.property_name} == null) {
    {$bean.property_name} = FXCollections.observableList(this.{$bean.name}());
}
return this.{$bean.property_name};

{#}


{#PropertyAddList}

if ({$bean.property_name} != null) {
	{$bean.property_name}.addAll(Arrays.asList({$bean.name}));
} else {
	{$bean.name}().addAll(Arrays.asList({$bean.name}));
}
return {$bean.fluent_type}this;

{#}


{#PropertyRemoveList}

if ({$bean.property_name} != null) {
	{$bean.property_name}.removeAll(Arrays.asList({$bean.name}));
} else {
	{$bean.name}().removeAll(Arrays.asList({$bean.name}));
}
return {$bean.fluent_type}this;

{#}


{#PropertyPutMap}

// TODO
return {$bean.fluent_type}this;

{#}


{#PropertyRemoveMap}

// TODO
return {$bean.fluent_type}this;

{#}

{#Operation}

{#}
