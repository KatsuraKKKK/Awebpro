# -*- coding: UTF-8 -*-
import requests
from bs4 import BeautifulSoup
import os


class meiTuLu:
    root_url = 'http://www.meitulu.com/t/yanni/'
    root_dir = '/Volumes/500G/SpiderImg'
    people_name = None
    headers = {'User-Agent': "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:51.0) Gecko/20100101 Firefox/51.0"}

    def __init__(self, root_url, root_dir, people_name):
        self.root_url = root_url
        self.root_dir = root_dir
        self.people_name = people_name

    def get_img_src(self, src, src_list):
        print('Get img src from ' + src)
        root_url = src
        root_html = requests.get(root_url, headers=self.headers)

        Soup = BeautifulSoup(root_html.content, 'lxml')
        img_list = Soup.find_all('img', 'content_img');

        for img in img_list:
            src = img['src']
            src_list.append(src)

        pages = Soup.find('div', id='pages').find_all('a')
        lastPage = pages[len(pages) - 2]['href']
        nextPage = pages[len(pages) - 1]['href']
        if lastPage != nextPage:
            self.get_img_src(nextPage, src_list)
        return src_list

    def make_dir(self, path):
        dir = os.path.join(self.root_dir, self.people_name, path)
        print('Make dir ' + dir)
        if not os.path.exists(dir):
            os.makedirs(dir, 0777)
        return dir

    def download_img(self, arch):
        src_list = []
        src_list = self.get_img_src(arch['href'], src_list)

        print('Download imgSrc...')
        for src in src_list:
            print(src)

        path = arch.get_text()
        img_dir = self.make_dir(path)
        os.chdir(img_dir)

        for src in src_list:
            print('downLoad:' + src)
            pos = src.rfind('/') + 1
            img_name = src[pos:]
            if not os.path.isfile(img_name):
                img = requests.get(src, headers=self.headers)
                f = open(img_name, 'ab')
                print('Save file [' + img_name + '] in dir [' + img_dir + ']')
                f.write(img.content)
                f.close()
            else:
                print(img_name + ' is exist.')

    def download_all_img(self):
        print('Collect img from ' + self.root_url)
        root_html = requests.get(self.root_url, headers=self.headers)
        Soup = BeautifulSoup(root_html.content, 'lxml')
        p_list = Soup.find('div', class_='boxs').find('ul').find_all('p', 'p_title')

        a_list = []
        for p in p_list:
            a_list.append(p.find('a'))

        for arch in a_list:
            self.download_img(arch)


MeiTuLu = meiTuLu('http://www.meitulu.com/t/yanni/', '/Volumes/500G/SpiderImg', unicode('王馨瑶yanni', "utf8"))
MeiTuLu.download_all_img()

MeiTuLu = meiTuLu('http://www.meitulu.com/t/cica-zhou/', '/Volumes/500G/SpiderImg', unicode('周韦彤', "utf8"))
MeiTuLu.download_all_img()

MeiTuLu = meiTuLu('http://www.meitulu.com/t/sayaka-tomaru/', '/Volumes/500G/SpiderImg', unicode('都丸纱也华|Sayaka Tomaru', "utf8"))
MeiTuLu.download_all_img()

MeiTuLu = meiTuLu('http://www.meitulu.com/t/arisa-nozaki/', '/Volumes/500G/SpiderImg', unicode('亚里沙|亜里沙 Arisa Nozaki', "utf8"))
MeiTuLu.download_all_img()
