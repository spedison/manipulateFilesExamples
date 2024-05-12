## This script show memory used by jvm processes.
while [[ true ]] ; do smem -k > /tmp/testMem 2>&1 ; clear ; date;  jps -q | xargs -I '{}' bash -c "cat /tmp/testMem | grep {} | grep sdkman | grep -v \"grep\" " ; sleep 1; done
