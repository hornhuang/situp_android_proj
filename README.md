# FIWKeepApp

![Version 1.0.2](https://img.shields.io/badge/SdkVersion-1.0.2-orange.svg?style=flat)
![SdkVersion 28](https://img.shields.io/badge/SdkVersion-28-green.svg?style=flat)
![rxjava2 2.2.3](https://img.shields.io/badge/rxjava2-2.2.3-red.svg?style=flat)
![rxandroid 2.1.0](https://img.shields.io/badge/rxandroid-2.1.0-grown.svg?style=flat)
![okhttp3 3.12.1](https://img.shields.io/badge/okhttp3-3.12.1-blue.svg?style=flat)
![glide 4.9.0](https://img.shields.io/badge/glide-4.9.0-green.svg?style=flat)

![中文文档](https://github.com/FishInWater-1999/FIWKeepApp/blob/master/README_cn.md)

a app like keep, you can edit your plans here, and you can share your life to others by post, too.

With the progress of science and technology and the popularity of the Internet, greatly changed the way and speed of information dissemination, effectively improved the efficiency of learning and work, so that people's pace of life is getting faster and faster, coupled with high density of learning and high pressure work, college students generally lack of exercise, leading to a decline in health.The health problem of college students has become a social problem, more and more college students realize the importance of fitness, which makes college students pay more and more attention to fitness.

Due to college students' free and flexible spare time, most of them use their time mainly in the aspects of associations, organizations, games or learning, which makes the time arrangement not reasonable and scientific and ignores the improvement of their physical quality.Therefore, based on the current situation, SitUp implements the calendar punch plan to train users to form good fitness habits.

# Effect sgraphic
#### Fingerprints and check-in
![指纹](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/Dec-23-2019%2009-24-30.gif)
![签到](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/Dec-23-2019%2009-25-13.gif)
![论坛](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/Dec-23-2019%2009-27-03.gif)</br>

![发文](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/Dec-23-2019%2009-27-53.gif)
![数据统计](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/Dec-23-2019%2009-29-57.gif)
![排行](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/Dec-23-2019%2009-36-53.gif)</br>

![数据统计](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/Dec-23-2019%2009-37-48.gif)
![排行](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/Dec-23-2019%2009-38-20.gif)
![数据统计](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/Dec-23-2019%2009-38-58.gif)</br>

# DESIGN

> Data requirements e-r diagram

![数据 E-R 图](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/%E5%9B%BE%E7%89%871.png)

# ARCHITECTURE

- some module like userCenter build with MVP architecture
- so with is MVP？ Jsut like

![](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/MVP_MVVM/mvp.png)



- and the other build with MVVM architecture
- so with is MVP？ Jsut like

![](https://github.com/FishInWater-1999/PictureRepository/blob/master/FIWKeepApp/MVP_MVVM/MVVM.png)

> MVVM stands for Model, View, ViewModel.

> Model: This holds the data of the application. It cannot directly talk to the View. Generally, it’s recommended to expose the data to the ViewModel through Observables.
> View: It represents the UI of the application devoid of any Application Logic. It observes the ViewModel.
> ViewModel: It acts as a link between the Model and the View. It’s responsible for transforming the data from the Model. It provides data streams to the View. It also uses hooks or callbacks to update the View. It’ll ask for the data from the Model.
> The following flow illustrates the core MVVM Pattern.

## DIFFERENCE

> ViewModel replaces the Presenter in the Middle Layer.
- The Presenter holds references to the View. The ViewModel doesn’t.
- The Presenter updates the View using the classical way (triggering methods).
- The ViewModel sends data streams.
- The Presenter and View are in a 1 to 1 relationship.
- The View and the ViewModel are in a 1 to many relationship.
- The ViewModel does not know that the View is listening to it.

> There are two ways to implement MVVM in Android:

- Data Binding
- RXJava

> In this tutorial, we’ll be using Data Binding only.
> Data Binding Library was introduced by Google in order to bind data directly in the xml layout. For more info on Data Binding, refer this tutorial.

- We’ll be creating a simple Login Page Example Application that asks for user inputs. We’ll see how the ViewModel notifies the View when to show a Toast Message without keeping a reference of the View.

# LICENSE
MIT License

Copyright (c) 2019 FishInWater-1999

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
