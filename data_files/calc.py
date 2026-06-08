#!python3
import os
import csv
import argparse
from pathlib import Path 
def t(in_file):
    with open(in_file,'r') as f:
        reader = csv.DictReader(f)
        data = list(reader) 
        nulls = []
        all_app = {} 
        out = []
        for d in data:
            try:
                d["appreciation"] = (1+((float(d["last_date"])/float(d["first_date"]))**(1/325)-1))**12-1
                d["appreciation"] = round(d["appreciation"]*100,3)
                
                all_app[d["appreciation"]] = d
                out.append({"zip_code":d["regionname"],"county_name":d["countyname"],'state':d['state'],'avg_home_price':d['last_date'],
                           'avg_annual_appreciation':d['appreciation']})
            except:
                nulls.append(d["appreciation"])
        q = list(all_app.keys())
        q.sort()
        print(q)
        print(all_app[q[0]],all_app[q[-1]])
        print(len(nulls))
    with open(in_file.name.split('.')[0]+"_appreciation_data.csv",'w') as f:
        writer = csv.DictWriter(f,fieldnames=['zip_code','county_name','state','avg_home_price','avg_annual_appreciation'])
        writer.writeheader()
        
        writer.writerows(out)
        print("output file written")
if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("file",type=Path)
    args = parser.parse_args()
    print(args.file)
    t(args.file)
