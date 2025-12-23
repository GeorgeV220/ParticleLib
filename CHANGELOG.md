# [1.6.0](https://github.com/GeorgeV220/ParticleLib/compare/v1.5.0...v1.6.0) (2025-12-23)


### Bug Fixes

* Add API version specification to plugin.yml ([51cad39](https://github.com/GeorgeV220/ParticleLib/commit/51cad39aaed8014efaf681c72e1c40285ae21515))
* add missing return statement in ChunkCommand ([95e6d27](https://github.com/GeorgeV220/ParticleLib/commit/95e6d27caadce8cde033704cc929ac382901f4e1))
* improve version detection and Spigot server compatibility ([e6830aa](https://github.com/GeorgeV220/ParticleLib/commit/e6830aafe5bc835880df70eb547bfb56ef6a98e3))
* **ParticleEffect:** add NONE particles to unsupported effects list ([1219c0f](https://github.com/GeorgeV220/ParticleLib/commit/1219c0fe5e652c9b862b863339cf82fdf45b2d14))
* **publish:** correct Maven coordinates for lib submodule ([df2703d](https://github.com/GeorgeV220/ParticleLib/commit/df2703df894bd0f8be4fdda606b89f9f9e3fed49))
* wrap chunk particle task in try-catch and change to sync scheduler ([28f132b](https://github.com/GeorgeV220/ParticleLib/commit/28f132b8da2ff64349104b56084ec9ae17c9b40f))


### Features

* add chunk visualization feature and scheduler abstraction ([0df789f](https://github.com/GeorgeV220/ParticleLib/commit/0df789fc312a093abde75accf8bc2d336045ee58))
* add getMappings method for unmodifiable view of particle mappings ([381c98b](https://github.com/GeorgeV220/ParticleLib/commit/381c98bc4f17c333e932032b1fa6817d256f3853))
* add Minecraft version 1.21.11 support ([79b2e6a](https://github.com/GeorgeV220/ParticleLib/commit/79b2e6af6286315cb3c007a83427384a9d6a9cf5))
* update Minecraft version and improve NMS particle resolution ([dd0cd91](https://github.com/GeorgeV220/ParticleLib/commit/dd0cd91bdd42ec03e525a440340da9e453bb8a0d))

# [1.6.0-alpha.6](https://github.com/GeorgeV220/ParticleLib/compare/v1.6.0-alpha.5...v1.6.0-alpha.6) (2025-12-22)


### Bug Fixes

* **ParticleEffect:** add NONE particles to unsupported effects list ([1219c0f](https://github.com/GeorgeV220/ParticleLib/commit/1219c0fe5e652c9b862b863339cf82fdf45b2d14))

# [1.6.0-alpha.5](https://github.com/GeorgeV220/ParticleLib/compare/v1.6.0-alpha.4...v1.6.0-alpha.5) (2025-12-15)


### Bug Fixes

* **publish:** correct Maven coordinates for lib submodule ([df2703d](https://github.com/GeorgeV220/ParticleLib/commit/df2703df894bd0f8be4fdda606b89f9f9e3fed49))

# [1.6.0-alpha.4](https://github.com/GeorgeV220/ParticleLib/compare/v1.6.0-alpha.3...v1.6.0-alpha.4) (2025-12-15)


### Bug Fixes

* Add API version specification to plugin.yml ([51cad39](https://github.com/GeorgeV220/ParticleLib/commit/51cad39aaed8014efaf681c72e1c40285ae21515))
* add missing return statement in ChunkCommand ([95e6d27](https://github.com/GeorgeV220/ParticleLib/commit/95e6d27caadce8cde033704cc929ac382901f4e1))
* improve version detection and Spigot server compatibility ([e6830aa](https://github.com/GeorgeV220/ParticleLib/commit/e6830aafe5bc835880df70eb547bfb56ef6a98e3))
* wrap chunk particle task in try-catch and change to sync scheduler ([28f132b](https://github.com/GeorgeV220/ParticleLib/commit/28f132b8da2ff64349104b56084ec9ae17c9b40f))


### Features

* add chunk visualization feature and scheduler abstraction ([0df789f](https://github.com/GeorgeV220/ParticleLib/commit/0df789fc312a093abde75accf8bc2d336045ee58))

# [1.6.0-alpha.3](https://github.com/GeorgeV220/ParticleLib/compare/v1.6.0-alpha.2...v1.6.0-alpha.3) (2025-12-10)


### Features

* add getMappings method for unmodifiable view of particle mappings ([381c98b](https://github.com/GeorgeV220/ParticleLib/commit/381c98bc4f17c333e932032b1fa6817d256f3853))

# [1.6.0-alpha.2](https://github.com/GeorgeV220/ParticleLib/compare/v1.6.0-alpha.1...v1.6.0-alpha.2) (2025-12-10)


### Features

* update Minecraft version and improve NMS particle resolution ([dd0cd91](https://github.com/GeorgeV220/ParticleLib/commit/dd0cd91bdd42ec03e525a440340da9e453bb8a0d))

# [1.6.0-alpha.1](https://github.com/GeorgeV220/ParticleLib/compare/v1.5.0...v1.6.0-alpha.1) (2025-12-10)


### Features

* add Minecraft version 1.21.11 support ([79b2e6a](https://github.com/GeorgeV220/ParticleLib/commit/79b2e6af6286315cb3c007a83427384a9d6a9cf5))

# [1.5.0](https://github.com/GeorgeV220/ParticleLib/compare/v1.4.0...v1.5.0) (2025-12-01)


### Bug Fixes

* improve version comparison logic for particle mappings ([3526f37](https://github.com/GeorgeV220/ParticleLib/commit/3526f37a5c1263fc5422ddae5847ee54cd9165b7))


### Features

* 1.21.10 and better version checkup ([cc1b49c](https://github.com/GeorgeV220/ParticleLib/commit/cc1b49cc87814b14aa08886a9a2cdf81ca7e11a3))
* filter out null NMS objects in ParticleEffect initialization ([f9139c9](https://github.com/GeorgeV220/ParticleLib/commit/f9139c976ff0d7da3a7be1c09c8e185baee8a4d5))
* improve particle effect initialization and add unsupported effects tracking ([4892ed8](https://github.com/GeorgeV220/ParticleLib/commit/4892ed80d3e2b044d345a5559f3f1ab6feccce0f))
* replace double version checks with MinecraftVersion enum ([32873b3](https://github.com/GeorgeV220/ParticleLib/commit/32873b334792564da560cf4ffa366ab2f25eb2ed))

# [1.5.0-alpha.4](https://github.com/GeorgeV220/ParticleLib/compare/v1.5.0-alpha.3...v1.5.0-alpha.4) (2025-11-20)


### Features

* improve particle effect initialization and add unsupported effects tracking ([4892ed8](https://github.com/GeorgeV220/ParticleLib/commit/4892ed80d3e2b044d345a5559f3f1ab6feccce0f))

# [1.5.0-alpha.3](https://github.com/GeorgeV220/ParticleLib/compare/v1.5.0-alpha.2...v1.5.0-alpha.3) (2025-11-20)


### Bug Fixes

* improve version comparison logic for particle mappings ([3526f37](https://github.com/GeorgeV220/ParticleLib/commit/3526f37a5c1263fc5422ddae5847ee54cd9165b7))

# [1.5.0-alpha.2](https://github.com/GeorgeV220/ParticleLib/compare/v1.5.0-alpha.1...v1.5.0-alpha.2) (2025-11-20)


### Features

* replace double version checks with MinecraftVersion enum ([32873b3](https://github.com/GeorgeV220/ParticleLib/commit/32873b334792564da560cf4ffa366ab2f25eb2ed))

# [1.5.0-alpha.1](https://github.com/GeorgeV220/ParticleLib/compare/v1.4.0...v1.5.0-alpha.1) (2025-11-20)


### Features

* filter out null NMS objects in ParticleEffect initialization ([f9139c9](https://github.com/GeorgeV220/ParticleLib/commit/f9139c976ff0d7da3a7be1c09c8e185baee8a4d5))

# [1.4.0](https://github.com/GeorgeV220/ParticleLib/compare/v1.3.2...v1.4.0) (2025-10-27)


### Features

* 1.21.5 - 1.21.10 support ([#8](https://github.com/GeorgeV220/ParticleLib/issues/8)) ([0644867](https://github.com/GeorgeV220/ParticleLib/commit/0644867081747dca55d6c418a63e8b9cba17b536))

## [1.3.2](https://github.com/GeorgeV220/ParticleLib/compare/v1.3.1...v1.3.2) (2024-12-27)


### Bug Fixes

* update min version for TrailParticleOption and particle effect fixes ([#7](https://github.com/GeorgeV220/ParticleLib/issues/7)) ([67dc21a](https://github.com/GeorgeV220/ParticleLib/commit/67dc21ac8abf9529d058cbd90c5091e375ee83fd))

## [1.3.1](https://github.com/GeorgeV220/ParticleLib/compare/v1.3.0...v1.3.1) (2024-12-23)


### Bug Fixes

* Account for final 1.21.4 protocol changes ([#6](https://github.com/GeorgeV220/ParticleLib/issues/6)) ([b71aadb](https://github.com/GeorgeV220/ParticleLib/commit/b71aadb50dd51dfbd5ef2c6fc068f4ac7000be8f))

# [1.3.0](https://github.com/GeorgeV220/ParticleLib/compare/v1.2.0...v1.3.0) (2024-12-18)


### Features

* 1.21.3 and 1.21.4 support ([#5](https://github.com/GeorgeV220/ParticleLib/issues/5)) ([6392ed1](https://github.com/GeorgeV220/ParticleLib/commit/6392ed1c33307f202036e0a130e274de204e65b9))

# [1.2.0](https://github.com/GeorgeV220/ParticleLib/compare/v1.1.0...v1.2.0) (2024-06-22)


### Features

* 1.21 support ([#4](https://github.com/GeorgeV220/ParticleLib/issues/4)) ([cfc4ead](https://github.com/GeorgeV220/ParticleLib/commit/cfc4ead048c40c8079ea9fbc80aebc5969e98f8f))

# [1.1.0](https://github.com/GeorgeV220/ParticleLib/compare/v1.0.0...v1.1.0) (2024-06-01)


### Bug Fixes

* fix some max values in mappings.json ([1871daf](https://github.com/GeorgeV220/ParticleLib/commit/1871daf18dd6fda49c081d18a7537350539701b8))


### Features

* 1.20.6 support & add missing particles from 1.20+ ([#2](https://github.com/GeorgeV220/ParticleLib/issues/2)) ([564c65f](https://github.com/GeorgeV220/ParticleLib/commit/564c65f3ce9e4cd71c66473868b1d8dc9bd06113))
* Add new mappings for values at index 20 ([f6e5fb8](https://github.com/GeorgeV220/ParticleLib/commit/f6e5fb8a1e860e87efe0351606c7942c792470d9))
* Add ParticleEffect.getParticle(String) and ParticleEffect.CONSTANT.getFieldNameMapper() ([0a75319](https://github.com/GeorgeV220/ParticleLib/commit/0a7531923fe84911fabf22b90e6689cfad198ea6))
* Add support for 1.20.3-1.20.4 ([772f0d1](https://github.com/GeorgeV220/ParticleLib/commit/772f0d143415f4f0158585682a54d40ecbbab11c))
* Initial 1.20.5 Support ([#1](https://github.com/GeorgeV220/ParticleLib/issues/1)) ([82c1de8](https://github.com/GeorgeV220/ParticleLib/commit/82c1de8e794a48f5758be1563407aa69052ec9bd))

# [1.1.0-alpha.6](https://github.com/GeorgeV220/ParticleLib/compare/v1.1.0-alpha.5...v1.1.0-alpha.6) (2024-06-01)


### Features

* 1.20.6 support & add missing particles from 1.20+ ([#2](https://github.com/GeorgeV220/ParticleLib/issues/2)) ([564c65f](https://github.com/GeorgeV220/ParticleLib/commit/564c65f3ce9e4cd71c66473868b1d8dc9bd06113))

# [1.1.0-alpha.5](https://github.com/GeorgeV220/ParticleLib/compare/v1.1.0-alpha.4...v1.1.0-alpha.5) (2024-06-01)


### Features

* Initial 1.20.5 Support ([#1](https://github.com/GeorgeV220/ParticleLib/issues/1)) ([82c1de8](https://github.com/GeorgeV220/ParticleLib/commit/82c1de8e794a48f5758be1563407aa69052ec9bd))

# [1.1.0-alpha.4](https://github.com/GeorgeV220/ParticleLib/compare/v1.1.0-alpha.3...v1.1.0-alpha.4) (2023-12-24)


### Features

* Add ParticleEffect.getParticle(String) and ParticleEffect.CONSTANT.getFieldNameMapper() ([0a75319](https://github.com/GeorgeV220/ParticleLib/commit/0a7531923fe84911fabf22b90e6689cfad198ea6))

# [1.1.0-alpha.3](https://github.com/GeorgeV220/ParticleLib/compare/v1.1.0-alpha.2...v1.1.0-alpha.3) (2023-12-24)


### Features

* Add support for 1.20.3-1.20.4 ([772f0d1](https://github.com/GeorgeV220/ParticleLib/commit/772f0d143415f4f0158585682a54d40ecbbab11c))

# [1.1.0-alpha.2](https://github.com/GeorgeV220/ParticleLib/compare/v1.1.0-alpha.1...v1.1.0-alpha.2) (2023-12-24)


### Bug Fixes

* fix some max values in mappings.json ([1871daf](https://github.com/GeorgeV220/ParticleLib/commit/1871daf18dd6fda49c081d18a7537350539701b8))

# [1.1.0-alpha.1](https://github.com/GeorgeV220/ParticleLib/compare/v1.0.0...v1.1.0-alpha.1) (2023-06-20)


### Features

* Add new mappings for values at index 20 ([f6e5fb8](https://github.com/GeorgeV220/ParticleLib/commit/f6e5fb8a1e860e87efe0351606c7942c792470d9))

# 1.0.0 (2023-06-13)


### Features

* **gradle:** Gradle project ([87d6c9c](https://github.com/GeorgeV220/ParticleLib/commit/87d6c9ce15eb2c5bb54562285b67a25bf7ddc535))
* **package:** Changed package name ([392fc0d](https://github.com/GeorgeV220/ParticleLib/commit/392fc0df7a3fc496e0aa4139705406029715c944))
