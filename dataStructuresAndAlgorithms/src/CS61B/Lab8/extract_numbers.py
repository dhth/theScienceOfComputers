from collections import defaultdict
f = open('lexi.md')

l = {}

for line in f:
    line = line.strip()
    if "#" in line:
        num_inserts = line.split(":")[-1].strip()
        if l.get(num_inserts, -1) == -1:
            l[num_inserts] = {}
    if "sec" in line:
        impl = line.split(":")[0].strip()
        secs = line.split(":")[-1].split("sec")[0].strip()
        l[num_inserts][impl] = secs
        
f.close()
import pprint

pprint.pprint(l)

import json
with open('lexi.json', 'w') as fp:
        json = json.dumps(l, indent=4)
        fp.write(json)
