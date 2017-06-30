#!/usr/bin/env python
# coding: utf8

import os


# 目录信息
result = []


def pathwalk(path='.', result=[]):
    dirlist = os.listdir(path=path)

    for item in dirlist:
        child = os.path.join(path, item)
        print(child)
        if os.path.isfile(child):
            if child.split('.')[-1] in ['java', 'md', 'xml', 'jsp', 'css', 'js']:
                if 'bootstrap' not in child:
                    result.append(child)
        else:
            pathwalk(child, result)


def count(result):
    lines = 0
    for file in result:
        # jsp页面的< 或者>等是非法的UTF-8字符，所以会导致出错。因此换成latin-1编码格式即可。
        with open(file, 'r', encoding="latin-1") as f:
            filelines = len(f.readlines())
            print("{} 有{}行代码.".format(file, filelines))
            lines += filelines
            f.close()
    return lines


if __name__ == "__main__":
    pathwalk(path='.', result=result)
    lines = count(result)
    print("本项目共有{}行代码。".format(lines))