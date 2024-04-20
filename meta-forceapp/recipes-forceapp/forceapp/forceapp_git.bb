# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# TODO: Set this  with the path to your forceapp rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
SRC_URI = "git://git@github.com/rojasx/final-project-forceapp.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
# TODO: set to reference a specific commit hash in your assignment repo
SRCREV = "e9831049121f9ecc16b6d1d211d16ad75ebd9252"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/forceapp"

# TODO: Add the forceapp application and any other files you need to install
# See https://git.yoctoproject.org/poky/plain/meta/conf/bitbake.conf?h=kirkstone
FILES:${PN} += "${datadir}/misc/set_ip.sh"
FILES:${PN} += "${datadir}/misc/test_gpio.py"

# TODO: customize these as necessary for any libraries you need for your application
# (and remove comment)
TARGET_LDFLAGS += "-pthread -lrt"
inherit update-rc.d
INITSCRIPT_PACKAGES="${PN}"
#INITSCRIPT_NAME:${PN}="aesdsocket-start-stop.sh"

do_configure () {
	:
}

# do_compile () {
# 	oe_runmake
# }

do_install () {
	# TODO: Install your binaries/scripts here.
	# Be sure to install the target directory with install -d first
	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-D
	# and
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-S
	# See example at https://github.com/cu-ecen-aeld/ecen5013-yocto/blob/ecen5013-hello-world/meta-ecen5013/recipes-ecen5013/ecen5013-hello-world/ecen5013-hello-world_git.bb

	install -d ${D}${datadir}
	install -d ${D}${datadir}/misc
	install -m 0755 ${S}/set_ip.sh ${D}${datadir}/misc/
	install -m 0755 ${S}/test_gpio.py ${D}${datadir}/misc/
}