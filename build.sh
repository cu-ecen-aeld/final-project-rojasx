#!/bin/bash
# Script to build image for qemu.
# Author: Siddhant Jajoo.

git submodule init
git submodule sync
git submodule update

# local.conf won't exist until this step on first execution
source poky/oe-init-build-env

# Start clean
bitbake -c cleanall core-image-forceapp

CONFLINE="MACHINE = \"raspberrypi4\""

#Create image of the type rpi-sdimg
IMAGE="IMAGE_FSTYPES = \"tar.xz ext3 rpi-sdimg\""

#Set GPU memory as minimum
MEMORY="GPU_MEM = \"16\""

#Licence
LICENCE="LICENSE_FLAGS_ACCEPTED = \"synaptics-killswitch\""

# INIT_MAN="INIT_MANAGER = \"systemd\""

cat conf/local.conf | grep "${CONFLINE}" > /dev/null
local_conf_info=$?

cat conf/local.conf | grep "${IMAGE}" > /dev/null
local_image_info=$?

cat conf/local.conf | grep "${MEMORY}" > /dev/null
local_memory_info=$?

cat conf/local.conf | grep "${LICENCE}" > /dev/null
local_licn_info=$?

# cat conf/local.conf | grep "${INIT_MAN}" > /dev/null
# local_init_man_info=$?

# Add above params to local.conf
if [ $local_conf_info -ne 0 ];then
	echo "Append ${CONFLINE} in the local.conf file"
	echo ${CONFLINE} >> conf/local.conf
	
else
	echo "${CONFLINE} already exists in the local.conf file"
fi

if [ $local_image_info -ne 0 ];then 
    echo "Append ${IMAGE} in the local.conf file"
	echo ${IMAGE} >> conf/local.conf
else
	echo "${IMAGE} already exists in the local.conf file"
fi

if [ $local_memory_info -ne 0 ];then
    echo "Append ${MEMORY} in the local.conf file"
	echo ${MEMORY} >> conf/local.conf
else
	echo "${MEMORY} already exists in the local.conf file"
fi

if [ $local_licn_info -ne 0 ];then
    echo "Append ${LICENCE} in the local.conf file"
	echo ${LICENCE} >> conf/local.conf
else
	echo "${LICENCE} already exists in the local.conf file"
fi

# if [ $local_init_man_info -ne 0 ];then
#     echo "Append ${INIT_MAN} in the local.conf file"
# 	echo ${INIT_MAN} >> conf/local.conf
# else
# 	echo "${INIT_MAN} already exists in the local.conf file"
# fi

# ADD raspberrypi layer
bitbake-layers show-layers | grep "meta-raspberrypi" > /dev/null
layer_info=$?

if [ $layer_info -ne 0 ];then
	echo "Adding meta-raspberrypi layer"
	bitbake-layers add-layer ../meta-raspberrypi
else
	echo "layer meta-raspberrypi already exists"
fi

# ADD oe layer
bitbake-layers show-layers | grep "meta-oe" > /dev/null
layer_info=$?

if [ $layer_info -ne 0 ];then
	echo "Adding meta-oe layer"
	bitbake-layers add-layer ../meta-openembedded/meta-oe
else
	echo "layer meta-oe already exists"
fi

# Add python layer
bitbake-layers show-layers | grep "meta-python" > /dev/null
layer_info=$?

if [ $layer_info -ne 0 ];then
    echo "Adding meta-python layer"
	bitbake-layers add-layer ../meta-openembedded/meta-python
else
	echo "layer meta-python already exists"
fi

# ADD forceapp layer
bitbake-layers show-layers | grep "meta-forceapp" > /dev/null
layer_info=$?

if [ $layer_info -ne 0 ];then
	echo "Adding meta-forceapp layer"
	bitbake-layers add-layer ../meta-forceapp
else
	echo "layer meta-forceapp already exists"
fi

set -e
bitbake core-image-forceapp
